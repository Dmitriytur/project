package ua.nure.tur.db.dao.mysql;

import ua.nure.tur.db.dao.UserDAO;
import ua.nure.tur.entities.Role;
import ua.nure.tur.entities.User;
import ua.nure.tur.entities.UserProfile;
import ua.nure.tur.exceptions.DataAccessException;

import java.sql.*;

import static ua.nure.tur.utils.ClosingUtils.close;

public class MysqlUserDAO implements UserDAO {


    private static final String FIND_USER_BY_USERNAME = "select * from users where user_name=?";

    private static final String INSERT_USER = "insert into users (user_name, password, email, role_id ,user_profile_id, lang) " +
            "values (?, ?, ?, 1, null, ?)";

    private static final String FIND_USER_BY_EMAIL = "select * from users where email=?";

    private static final String FIND_USER_BY_ID = "select * from users where id=?";

    private static final String UPDATE_USER = "update users set password=?, user_profile_id=?, lang=? where id=?";

    private static final String SELECT_USER_PROFILE = "select * from user_profiles where id=?";

    private static final String INSERT_USER_PROFILE = "insert into user_profiles values (default, ?, ?, ?, ?, ?)";

    private static final String UPDATE_PROFILE = "update user_profiles set first_name=?, last_name=?, city=?, address=?, zip_code=? " +
            "where id=?";

    private Connection connection;

    public MysqlUserDAO(Connection connection) {
        this.connection = connection;
    }

    private User extractUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setUserName(resultSet.getString("user_name"));
        user.setPassword(resultSet.getString("password"));
        user.setBalance(resultSet.getDouble("balance"));
        user.setBanned(resultSet.getBoolean("banned"));
        user.setEmail(resultSet.getString("email"));
        user.setLang(resultSet.getString("lang"));
        user.setRole(Role.getRole(resultSet.getInt("role_id")));
        user.setUserProfileId(resultSet.getLong("user_profile_id"));
        return user;
    }

    private void prepareInsertUser(PreparedStatement statement, User user) throws SQLException {
        int i = 1;
        statement.setString(i++, user.getUserName());
        statement.setString(i++, user.getPassword());
        statement.setString(i++, user.getEmail());
        statement.setString(i, user.getLang());
    }

    private UserProfile extractUserProfile(ResultSet resultSet) throws SQLException {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(resultSet.getLong("id"));
        userProfile.setFirstName(resultSet.getString("first_name"));
        userProfile.setLastName(resultSet.getString("last_name"));
        userProfile.setCity(resultSet.getString("city"));
        userProfile.setAddress(resultSet.getString("address"));
        userProfile.setZipCode(resultSet.getString("zip_code"));
        return userProfile;
    }


    private User find(String data, String query) throws SQLException {
        User user = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, data);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = extractUser(resultSet);
            }
        } finally {
            close(resultSet);
            close(statement);
        }
        return user;
    }

    @Override
    public User findUserByUsername(String username) throws DataAccessException {
        try {
            return find(username, FIND_USER_BY_USERNAME);
        } catch (SQLException e) {
            //TODO log exception
            System.out.println(e);
            throw new DataAccessException("Cannot get user by email", e);
        }
    }

    @Override
    public User findByEmail(String email) throws DataAccessException {
        try {
            return find(email, FIND_USER_BY_EMAIL);
        } catch (SQLException e) {
            //TODO log exception
            System.out.println(e);
            throw new DataAccessException("Cannot get user by email", e);
        }
    }

    @Override
    public User findById(Long userId) throws DataAccessException {
        User user = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(FIND_USER_BY_ID);
            statement.setLong(1, userId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = extractUser(resultSet);
            }
        } catch (SQLException e) {
            //TODO log
            System.out.println(e);
            throw new DataAccessException("Cannot get user by id", e);
        } finally {
            close(resultSet);
            close(statement);
        }
        return user;
    }

    @Override
    public void update(User user) throws DataAccessException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(UPDATE_USER);
            int i = 1;
            statement.setString(i++, user.getPassword());
            statement.setLong(i++, user.getUserProfileId());
            statement.setString(i++, user.getLang());
            statement.setLong(i, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            //TODO log
            System.out.println(e.getMessage());
            throw new DataAccessException("Cannot update user", e);
        } finally {
            close(statement);
        }
    }

    @Override
    public UserProfile getUserProfile(Long userId) throws DataAccessException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(SELECT_USER_PROFILE);
            statement.setLong(1, userId);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                return extractUserProfile(resultSet);
            }
        } catch (SQLException e) {
            //TODO log
            System.out.println(e);
            throw new DataAccessException("Cannot get user profile", e);
        } finally {
            close(resultSet);
            close(statement);
        }
        return null;
    }

    @Override
    public void addUserProfile(UserProfile profile) throws DataAccessException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(INSERT_USER_PROFILE, Statement.RETURN_GENERATED_KEYS);
            prepareInsertProfile(statement, profile);
            if (statement.executeUpdate() > 0) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    profile.setId(resultSet.getLong(1));
                }
            }
        } catch (SQLException e) {
            //TODO log exception
            System.out.println(e.getMessage());
            throw new DataAccessException("Cannot insert new user profile", e);
        } finally {
            close(resultSet);
            close(statement);
        }
    }

    @Override
    public void updateUserProfile(UserProfile profile) throws DataAccessException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_PROFILE);
            int i = prepareInsertProfile(statement, profile);
            statement.setLong(i, profile.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            //TODO log
            System.out.println(e.getMessage());
            throw new DataAccessException("Cannot update user profile", e);
        } finally {
            close(statement);
        }
    }

    private int prepareInsertProfile(PreparedStatement statement, UserProfile profile) throws SQLException {
        int i = 1;
        statement.setString(i++, profile.getFirstName());
        statement.setString(i++, profile.getLastName());
        statement.setString(i++, profile.getCity());
        statement.setString(i++, profile.getAddress());
        statement.setString(i++, profile.getZipCode());
        return i;
    }

    @Override
    public void addUser(User user) throws DataAccessException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            prepareInsertUser(statement, user);
            if (statement.executeUpdate() > 0) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    user.setId(resultSet.getLong(1));
                }
            }
        } catch (SQLException e) {
            //TODO log exception
            System.out.println(e.getMessage());
            throw new DataAccessException("Cannot insert new user", e);
        } finally {
            close(resultSet);
            close(statement);
        }

    }


}

package ua.nure.tur.db.dao.mysql;

import ua.nure.tur.db.dao.UserDAO;
import ua.nure.tur.entities.Role;
import ua.nure.tur.entities.User;
import ua.nure.tur.entities.UserProfile;
import ua.nure.tur.exceptions.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static ua.nure.tur.utils.ClosingUtils.close;

public class MysqlUserDAO implements UserDAO {


    private static final String FIND_USER_BY_USERNAME = "select * from users where user_name=?";

    private static final String INSERT_USER = "insert into users (user_name, password, email, role_id ,user_profile_id, lang) " +
            "values (?, ?, ?, 1, null, ?)";

    private static final String FIND_USER_BY_EMAIL ="select * from users where email=?";

    private Connection connection;

    public MysqlUserDAO(Connection connection) {
        this.connection = connection;
    }

    private User extractUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUserName(resultSet.getString("user_name"));
        user.setPassword(resultSet.getString("password"));
        user.setBalance(resultSet.getDouble("balance"));
        user.setBanned(resultSet.getBoolean("banned"));
        user.setEmail(resultSet.getString("email"));
        user.setLang(resultSet.getString("lang"));
        user.setRole(Role.getRole(resultSet.getInt("role_id")));
        user.setUserProfileId(resultSet.getInt("user_profile_id"));
        return user;
    }

    private void prepareInsertStatement(PreparedStatement statement, User user) throws SQLException {
        int i = 1;
        statement.setString(i++, user.getUserName());
        statement.setString(i++, user.getPassword());
        statement.setString(i++, user.getEmail());
        statement.setString(i, user.getLang());
    }

    private UserProfile extractUserProfile(ResultSet resultSet) throws SQLException {
        UserProfile userProfile = new UserProfile();
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
            if (resultSet.next()){
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
    public void addUser(User user) throws DataAccessException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_USER);
            prepareInsertStatement(statement, user);
            statement.executeUpdate();
        } catch (SQLException e) {
            //TODO log exception
            System.out.println(e.getMessage());
            throw new DataAccessException("Cannot insert new user", e);
        } finally {
            close(statement);
        }

    }





}

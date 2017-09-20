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


    private static final String SQL_FIND_USER_BY_USERNAME = "select id, user_name, password, email, balance, banned, role_id from users " +
            "where user_name=?";

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
        user.setLang(resultSet.getString("lang"));
        user.setRole(Role.getRole(resultSet.getInt("role_id")));
        return user;
    }

    private UserProfile extractUserProfile(ResultSet resultSet) throws SQLException {
        UserProfile userProfile = new UserProfile();
        userProfile.setEmail(resultSet.getString("email"));
        userProfile.setFirstName(resultSet.getString("first_name"));
        userProfile.setLastName(resultSet.getString("last_name"));
        userProfile.setCity(resultSet.getString("city"));
        userProfile.setAddress(resultSet.getString("address"));
        userProfile.setZipCode(resultSet.getString("zip_code"));
        return userProfile;
    }

    @Override
    public User findUserByUsername(String username) throws DataAccessException {
        User user = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_USER_BY_USERNAME);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                user = extractUser(resultSet);
            }
        } catch (SQLException e) {
            //TODO log exception
            throw new DataAccessException("Cannot get user by username", e);
        } finally {
            close(resultSet);
            close(statement);
        }
        return user;
    }


}

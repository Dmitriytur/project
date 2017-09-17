package ua.nure.tur.db.dao.mysql;

import ua.nure.tur.db.dao.UserDAO;
import ua.nure.tur.entities.Role;
import ua.nure.tur.entities.User;
import ua.nure.tur.entities.UserProfile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlUserDAO implements UserDAO {

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


}

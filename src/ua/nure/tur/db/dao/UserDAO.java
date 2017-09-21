package ua.nure.tur.db.dao;

import ua.nure.tur.entities.User;
import ua.nure.tur.entities.UserProfile;
import ua.nure.tur.exceptions.DataAccessException;

public interface UserDAO {

    User findUserByUsername(String username) throws DataAccessException;

    void addUser(User user) throws DataAccessException;

    User findByEmail(String email) throws DataAccessException;

    User findById(Long userId) throws DataAccessException;

    void update(User user) throws DataAccessException;

    UserProfile getUserProfile(Long id) throws DataAccessException;

    void addUserProfile(UserProfile profile) throws DataAccessException;

    void updateUserProfile(UserProfile profile) throws DataAccessException;
}

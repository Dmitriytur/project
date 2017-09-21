package ua.nure.tur.db.dao;

import ua.nure.tur.entities.User;
import ua.nure.tur.exceptions.DataAccessException;

public interface UserDAO {

    User findUserByUsername(String username) throws DataAccessException;

    void addUser(User user) throws DataAccessException;

    User findByEmail(String email) throws DataAccessException;

    User findById(Long userId) throws DataAccessException;
}

package ua.nure.tur.services;

import ua.nure.tur.entities.User;
import ua.nure.tur.entities.UserProfile;
import ua.nure.tur.exceptions.ServiceException;

public interface UserService {

    User checkUser(String username, String password) throws ServiceException;

    ServiceResult<User> registerClient(User user) throws ServiceException;

    User getUserById(Long userId) throws ServiceException;

    ServiceResult<String> updateUserPassword(Long userId, String oldPassword, String password) throws ServiceException;

    UserProfile getUserProfileForUser(Long userId) throws ServiceException;

    void setProfile(Long userId, UserProfile profile) throws ServiceException;
}

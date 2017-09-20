package ua.nure.tur.services;

import ua.nure.tur.entities.User;
import ua.nure.tur.exceptions.RegistrationException;
import ua.nure.tur.exceptions.ServiceException;

public interface UserService {

    User checkUser(String username, String password) throws ServiceException;

    void registerClient(User user) throws ServiceException, RegistrationException;
}

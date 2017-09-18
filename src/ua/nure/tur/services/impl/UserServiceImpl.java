package ua.nure.tur.services.impl;

import ua.nure.tur.db.dao.DAOManager;
import ua.nure.tur.db.dao.DAOManagerFactory;
import ua.nure.tur.entities.User;
import ua.nure.tur.exceptions.DataAccessException;
import ua.nure.tur.exceptions.ServiceException;
import ua.nure.tur.services.UserService;
import ua.nure.tur.utils.ClosingUtils;

import static ua.nure.tur.utils.ClosingUtils.close;

public class UserServiceImpl implements UserService {

    private DAOManagerFactory daoManagerFactory;

    public UserServiceImpl(DAOManagerFactory daoManagerFactory) {
        this.daoManagerFactory = daoManagerFactory;
    }

    @Override
    public User checkUser(String username, String password) throws ServiceException {
        DAOManager daoManager = null;

        try {
            daoManager = daoManagerFactory.getDaoManager();
            User user = daoManager.getUserDao().findUserByUsername(username);
            if (user != null && password.equals(user.getPassword())){
                return user;
            } else {
                return null;
            }
        } catch (DataAccessException e) {
            throw new ServiceException("Cannot authorize user", e);
        } finally {
            close(daoManager);
        }
    }
}

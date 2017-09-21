package ua.nure.tur.services.impl;

import ua.nure.tur.db.dao.DAOManager;
import ua.nure.tur.db.dao.DAOManagerFactory;
import ua.nure.tur.db.dao.UserDAO;
import ua.nure.tur.entities.User;
import ua.nure.tur.exceptions.DataAccessException;
import ua.nure.tur.exceptions.ServiceException;
import ua.nure.tur.services.ServiceResult;
import ua.nure.tur.services.ServiceResultStatus;
import ua.nure.tur.services.UserService;

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
            if (user != null && password.equals(user.getPassword())) {
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

    @Override
    public ServiceResult<User> registerClient(User user) throws ServiceException {
        User addedUser = null;
        DAOManager daoManager = null;
        try {
            daoManager = daoManagerFactory.getDaoManager();
            daoManager.startTransaction();
            UserDAO userDAO = daoManager.getUserDao();
            boolean success = userDAO.findUserByUsername(user.getUserName()) == null;
            if (!success) {
                return new ServiceResult<>(ServiceResultStatus.FAIL, "Username isn't available");
            }
            success = userDAO.findByEmail(user.getEmail()) == null;
            if (!success) {
                return new ServiceResult<>(ServiceResultStatus.FAIL, "Email isn't available");
            }
            userDAO.addUser(user);
            addedUser = userDAO.findById(user.getId());
            daoManager.commit();
        } catch (DataAccessException e) {
            if (daoManager != null) {
                daoManager.rollback();
            }
            throw new ServiceException("Cannot register user", e);
        } finally {
            close(daoManager);
        }
        return new ServiceResult<>(ServiceResultStatus.SUCCESS, "", addedUser);
    }
}

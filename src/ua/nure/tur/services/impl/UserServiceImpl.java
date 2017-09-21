package ua.nure.tur.services.impl;

import ua.nure.tur.db.dao.DAOManager;
import ua.nure.tur.db.dao.DAOManagerFactory;
import ua.nure.tur.db.dao.UserDAO;
import ua.nure.tur.entities.User;
import ua.nure.tur.entities.UserProfile;
import ua.nure.tur.exceptions.DataAccessException;
import ua.nure.tur.exceptions.ServiceException;
import ua.nure.tur.services.ServiceResult;
import ua.nure.tur.services.ServiceResultStatus;
import ua.nure.tur.services.UserService;
import ua.nure.tur.utils.Pages;

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

    @Override
    public User getUserById(Long userId) throws ServiceException {
        DAOManager daoManager = null;

        try {
            daoManager = daoManagerFactory.getDaoManager();
            return daoManager.getUserDao().findById(userId);
        } catch (DataAccessException e) {
            throw new ServiceException("Cannot get user by id", e);
        } finally {
            close(daoManager);
        }
    }

    @Override
    public ServiceResult<String> updateUserPassword(Long userId, String oldPassword, String password) throws ServiceException {
        DAOManager daoManager = null;

        try {
            daoManager = daoManagerFactory.getDaoManager();
            User user = daoManager.getUserDao().findById(userId);
            if (user.getPassword().equals(oldPassword)){
                user.setPassword(password);
                daoManager.getUserDao().update(user);
            } else {
                return new ServiceResult<String>(ServiceResultStatus.FAIL, "Wrong password");
            }
        } catch (DataAccessException e) {
            throw new ServiceException("Cannot get user by id", e);
        } finally {
            close(daoManager);
        }
        return new ServiceResult<String>(ServiceResultStatus.SUCCESS, "Password was updated");
    }

    @Override
    public UserProfile getUserProfileForUser(Long userId) throws ServiceException {
        DAOManager daoManager = null;

        try {
            daoManager = daoManagerFactory.getDaoManager();
            User user = daoManager.getUserDao().findById(userId);
            return daoManager.getUserDao().getUserProfile(user.getUserProfileId());
        } catch (DataAccessException e) {
            throw new ServiceException("Cannot get user profile", e);
        } finally {
            close(daoManager);
        }
    }

    @Override
    public void setProfile(Long userId, UserProfile profile) throws ServiceException {
        DAOManager daoManager = null;

        try {
            daoManager = daoManagerFactory.getDaoManager();
            daoManager.startTransaction();
            User user = daoManager.getUserDao().findById(userId);
            System.out.println(user);
            if (user.getUserProfileId() == null){
                daoManager.getUserDao().addUserProfile(profile);
                user.setUserProfileId(profile.getId());
                System.out.println(user);
            } else {
                profile.setId(user.getUserProfileId());
                daoManager.getUserDao().updateUserProfile(profile);
            }
            daoManager.commit();
        } catch (DataAccessException e) {
            if (daoManager != null) {
                daoManager.rollback();
            }
            throw new ServiceException("Cannot set profile for user", e);
        } finally {
            close(daoManager);
        }
    }
}

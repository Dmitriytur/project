package ua.nure.tur.services.impl;

import ua.nure.tur.db.dao.DAOManagerFactory;
import ua.nure.tur.services.UserService;

public class UserServiceImpl implements UserService {

    private DAOManagerFactory daoManagerFactory;

    public UserServiceImpl(DAOManagerFactory daoManagerFactory) {
        this.daoManagerFactory = daoManagerFactory;
    }
}

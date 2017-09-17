package ua.nure.tur.services.impl;

import ua.nure.tur.db.dao.DAOManagerFactory;
import ua.nure.tur.services.ReviewService;

public class ReviewServiceImpl implements ReviewService {

    private DAOManagerFactory daoManagerFactory;

    public ReviewServiceImpl(DAOManagerFactory daoManagerFactory) {
        this.daoManagerFactory = daoManagerFactory;
    }
}

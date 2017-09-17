package ua.nure.tur.services.impl;

import ua.nure.tur.db.dao.DAOManagerFactory;
import ua.nure.tur.services.SubscriptionService;

public class SubscriptionServiceImpl implements SubscriptionService {

    private DAOManagerFactory daoManagerFactory;

    public SubscriptionServiceImpl(DAOManagerFactory daoManagerFactory) {
        this.daoManagerFactory = daoManagerFactory;
    }
}

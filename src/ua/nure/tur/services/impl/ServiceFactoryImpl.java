package ua.nure.tur.services.impl;

import ua.nure.tur.db.dao.DAOManagerFactory;
import ua.nure.tur.services.*;

public class ServiceFactoryImpl extends ServiceFactory {


    private DAOManagerFactory daoManagerFactory;

    private volatile UserService userService;

    private volatile PeriodicalService periodicalService;

    private volatile SubscriptionService subscriptionService;

    private volatile ReviewService reviewService;


    public ServiceFactoryImpl(DAOManagerFactory daoManagerFactory) {
        this.daoManagerFactory = daoManagerFactory;
    }

    @Override
    public UserService getUserService() {
        UserService localInstance = userService;
        if (localInstance == null) {
            synchronized (UserService.class) {
                localInstance = userService;
                if (localInstance == null) {
                    userService = localInstance = new UserServiceImpl(daoManagerFactory);
                }
            }
        }
        return localInstance;
    }

    @Override
    public PeriodicalService getPeriodicalService() {
        PeriodicalService localInstance = periodicalService;
        if (localInstance == null) {
            synchronized (PeriodicalService.class) {
                localInstance = periodicalService;
                if (localInstance == null) {
                    periodicalService = localInstance = new PeriodicalServiceImpl(daoManagerFactory);
                }
            }
        }
        return localInstance;
    }

    @Override
    public SubscriptionService getSubscriptionService() {
        SubscriptionService localInstance = subscriptionService;
        if (localInstance == null) {
            synchronized (SubscriptionService.class) {
                localInstance = subscriptionService;
                if (localInstance == null) {
                    subscriptionService = localInstance = new SubscriptionServiceImpl(daoManagerFactory);
                }
            }
        }
        return localInstance;
    }

    @Override
    public ReviewService getReviewService() {
        ReviewService localInstance = reviewService;
        if (localInstance == null) {
            synchronized (ReviewService.class) {
                localInstance = reviewService;
                if (localInstance == null) {
                    reviewService = localInstance = new ReviewServiceImpl(daoManagerFactory);
                }
            }
        }
        return localInstance;
    }
}

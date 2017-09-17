package ua.nure.tur.db.dao;

import ua.nure.tur.exceptions.DataAccessException;

public interface DAOManager extends AutoCloseable {

    UserDAO getUserDao();

    PeriodicalDAO getPeriodicalDAO();

    SubscriptionDAO getSubscriptionDao();

    ReviewDAO getReviewDao();

    void startTransaction() throws DataAccessException;

    void commit() throws DataAccessException;

    void rollback();

}

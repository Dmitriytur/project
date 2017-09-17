package ua.nure.tur.db.dao.mysql;

import ua.nure.tur.db.dao.*;
import ua.nure.tur.exceptions.DataAccessException;
import ua.nure.tur.utils.ClosingUtils;

import java.sql.Connection;
import java.sql.SQLException;


public class MysqlDAOManager implements DAOManager {

    private Connection connection;

    private UserDAO userDAO;

    private PeriodicalDAO periodicalDAO;

    private SubscriptionDAO subscriptionDAO;

    private ReviewDAO reviewDAO;


    public MysqlDAOManager(Connection connection) {
        this.connection = connection;
    }


    @Override
    public UserDAO getUserDao() {
        if (userDAO == null) {
            userDAO = new MysqlUserDAO(connection);
        }
        return userDAO;
    }

    @Override
    public PeriodicalDAO getPeriodicalDAO() {
        if (periodicalDAO == null) {
            periodicalDAO = new MysqlPeriodicalDAO(connection);
        }
        return periodicalDAO;
    }

    @Override
    public SubscriptionDAO getSubscriptionDao() {
        if (subscriptionDAO == null) {
            subscriptionDAO = new MysqlSubscriptionDAO(connection);
        }
        return subscriptionDAO;
    }


    @Override
    public ReviewDAO getReviewDao() {
        if (reviewDAO == null) {
            reviewDAO = new MysqlReviewDAO(connection);
        }
        return reviewDAO;
    }

    @Override
    public void startTransaction() throws DataAccessException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            //TODO: log exceptions
            throw new DataAccessException("Cannot set connection autoCommit", e);
        }
    }

    @Override
    public void commit() throws DataAccessException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            //TODO: log exceptions
            throw new DataAccessException("Cannot commit transaction", e);
        }
    }

    @Override
    public void rollback()  {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            //TODO: log exceptions

        }
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }
}

package ua.nure.tur.db.dao.mysql;

import ua.nure.tur.db.dao.DAOManager;
import ua.nure.tur.db.dao.DAOManagerFactory;
import ua.nure.tur.exceptions.DataAccessException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MysqlDAOManagerFactory implements DAOManagerFactory {

    private DataSource dataSource;

    public MysqlDAOManagerFactory() throws DataAccessException {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");

            dataSource = (DataSource) envContext.lookup("jdbc/summaryProjectDb");
        } catch (NamingException e) {
            //TODO: log exceptions
            throw new DataAccessException("Can't get DataSource", e);
        }
    }

    @Override
    public DAOManager getDaoManager() throws DataAccessException {
        Connection connection;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            //TODO: log exceptions
            throw new DataAccessException("Cannot get connection from DataSource", e);
        }
        return new MysqlDAOManager(connection);
    }
}

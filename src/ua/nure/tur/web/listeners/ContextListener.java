package ua.nure.tur.web.listeners;

import ua.nure.tur.db.dao.DAOManagerFactory;
import ua.nure.tur.db.dao.mysql.MysqlDAOManagerFactory;
import ua.nure.tur.exceptions.DataAccessException;
import ua.nure.tur.services.ServiceFactory;
import ua.nure.tur.services.impl.ServiceFactoryImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            DAOManagerFactory daoManagerFactory = new MysqlDAOManagerFactory();
            ServiceFactory.setFactory(new ServiceFactoryImpl(daoManagerFactory));
        } catch (DataAccessException e) {
            //TODO: log exceptions
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}

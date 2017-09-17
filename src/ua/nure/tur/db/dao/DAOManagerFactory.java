package ua.nure.tur.db.dao;

import ua.nure.tur.exceptions.DataAccessException;

public interface DAOManagerFactory {

    DAOManager getDaoManager() throws DataAccessException;
}

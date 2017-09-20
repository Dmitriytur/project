package ua.nure.tur.services.impl;

import ua.nure.tur.db.dao.DAOManager;
import ua.nure.tur.db.dao.DAOManagerFactory;
import ua.nure.tur.entities.Periodical;
import ua.nure.tur.exceptions.DataAccessException;
import ua.nure.tur.exceptions.ServiceException;
import ua.nure.tur.services.PeriodicalService;

import java.util.List;

import static ua.nure.tur.utils.ClosingUtils.close;

public class PeriodicalServiceImpl implements PeriodicalService {

    private DAOManagerFactory daoManagerFactory;

    public PeriodicalServiceImpl(DAOManagerFactory daoManagerFactory) {
        this.daoManagerFactory = daoManagerFactory;
    }

    @Override
    public List<Periodical> findAll() throws ServiceException {
        DAOManager daoManager = null;
        try {
            daoManager = daoManagerFactory.getDaoManager();
            return daoManager.getPeriodicalDAO().findAll();
        } catch (DataAccessException e) {
            throw new ServiceException("Cannot get all periodicals", e);
        } finally {
            close(daoManager);
        }
    }

    @Override
    public Periodical getById(int id) throws ServiceException {
        DAOManager daoManager = null;
        try {
            daoManager = daoManagerFactory.getDaoManager();
            return daoManager.getPeriodicalDAO().getById(id);
        } catch (DataAccessException e) {
            throw new ServiceException("Cannot get periodical by id", e);
        } finally {
            close(daoManager);
        }
    }
}

package ua.nure.tur.services.impl;

import ua.nure.tur.db.dao.DAOManager;
import ua.nure.tur.db.dao.DAOManagerFactory;
import ua.nure.tur.entities.Review;
import ua.nure.tur.exceptions.DataAccessException;
import ua.nure.tur.exceptions.ServiceException;
import ua.nure.tur.services.ReviewService;
import ua.nure.tur.utils.ClosingUtils;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {

    private DAOManagerFactory daoManagerFactory;

    public ReviewServiceImpl(DAOManagerFactory daoManagerFactory) {
        this.daoManagerFactory = daoManagerFactory;
    }

    @Override
    public List<Review> findForPeriodical(Long id) throws ServiceException {
        DAOManager daoManager = null;
        try {
            daoManager = daoManagerFactory.getDaoManager();
            return daoManager.getReviewDao().findForPeriodical(id);
        } catch (DataAccessException e) {
            throw new ServiceException("Cannot find reviews for periodical", e);
        } finally {
            ClosingUtils.close(daoManager);
        }
    }
}

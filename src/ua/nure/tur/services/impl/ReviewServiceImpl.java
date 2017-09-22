package ua.nure.tur.services.impl;

import ua.nure.tur.db.dao.DAOManager;
import ua.nure.tur.db.dao.DAOManagerFactory;
import ua.nure.tur.entities.Periodical;
import ua.nure.tur.entities.Review;
import ua.nure.tur.exceptions.DataAccessException;
import ua.nure.tur.exceptions.ServiceException;
import ua.nure.tur.services.ReviewService;
import ua.nure.tur.utils.ClosingUtils;

import java.sql.Date;
import java.util.List;

import static ua.nure.tur.utils.ClosingUtils.close;

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
            close(daoManager);
        }
    }

    @Override
    public void addReview(Long userId, long periodicalId, int score, String message) throws ServiceException {
        DAOManager daoManager = null;

        Review review = new Review();
        review.setUserId(userId);
        review.setPeriodicalId(periodicalId);
        review.setMessage(message);
        review.setScore(score);
        review.setCreationDate(new Date(System.currentTimeMillis()));

        try {
            daoManager = daoManagerFactory.getDaoManager();
            daoManager.startTransaction();
            ;
            Periodical periodical = daoManager.getPeriodicalDAO().getById(periodicalId);
            int reviewAmount = daoManager.getReviewDao().getAmountForPeriodical(periodicalId);

            double newRating = (periodical.getRating() * reviewAmount + score) / (reviewAmount + 1);

            periodical.setSummaryRating(periodical.getSummaryRating() + score - 3);
            periodical.setRating(newRating);


            daoManager.getPeriodicalDAO().update(periodical);
            daoManager.getReviewDao().create(review);

            daoManager.commit();
        } catch (DataAccessException e) {
            if (daoManager != null) {
                daoManager.rollback();
            }
            throw new ServiceException("Cannot add review", e);
        } finally {
            close(daoManager);
        }
    }
}

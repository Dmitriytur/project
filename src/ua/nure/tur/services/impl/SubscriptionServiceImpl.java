package ua.nure.tur.services.impl;

import ua.nure.tur.db.dao.DAOManager;
import ua.nure.tur.db.dao.DAOManagerFactory;
import ua.nure.tur.exceptions.DataAccessException;
import ua.nure.tur.exceptions.ServiceException;
import ua.nure.tur.services.SubscriptionService;
import ua.nure.tur.utils.ClosingUtils;
import ua.nure.tur.utils.Pages;
import ua.nure.tur.viewmodels.UserSubscriptionViewModel;

import java.util.Date;
import java.util.List;

import static ua.nure.tur.utils.ClosingUtils.close;

public class SubscriptionServiceImpl implements SubscriptionService {

    private DAOManagerFactory daoManagerFactory;

    public SubscriptionServiceImpl(DAOManagerFactory daoManagerFactory) {
        this.daoManagerFactory = daoManagerFactory;
    }

    @Override
    public List<UserSubscriptionViewModel> getUserSubscriptions(Long userId) throws ServiceException {
        DAOManager daoManager = null;

        try {
            daoManager = daoManagerFactory.getDaoManager();
            List<UserSubscriptionViewModel> subscriptions = daoManager.getSubscriptionDao().getUserSubscriptions(userId);
            Date today = new Date();
            for (UserSubscriptionViewModel model : subscriptions){
                if (today.after(model.getSubscription().getEndDate())){
                    model.setActive(false);
                }
            }
            return subscriptions;
        } catch (DataAccessException e) {
            throw new ServiceException("Cannot get user subscriptions", e);
        } finally {
            close(daoManager);
        }

    }
}

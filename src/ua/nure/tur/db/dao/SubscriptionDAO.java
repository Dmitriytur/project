package ua.nure.tur.db.dao;

import ua.nure.tur.entities.Subscription;
import ua.nure.tur.exceptions.DataAccessException;
import ua.nure.tur.viewmodels.UserSubscriptionViewModel;

import java.util.List;

public interface SubscriptionDAO {
    List<UserSubscriptionViewModel> getUserSubscriptions(Long userId) throws DataAccessException;

    void addSubscription(Subscription subscription) throws DataAccessException;
}

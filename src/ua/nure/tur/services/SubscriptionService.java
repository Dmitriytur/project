package ua.nure.tur.services;

import ua.nure.tur.exceptions.ServiceException;
import ua.nure.tur.viewmodels.UserSubscriptionViewModel;

import java.util.List;

public interface SubscriptionService {

    List<UserSubscriptionViewModel> getUserSubscriptions(Long userId) throws ServiceException;
}

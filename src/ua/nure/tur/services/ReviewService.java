package ua.nure.tur.services;

import ua.nure.tur.entities.Review;
import ua.nure.tur.exceptions.ServiceException;

import java.util.List;

public interface ReviewService {
    List<Review> findForPeriodical(int id) throws ServiceException;
}

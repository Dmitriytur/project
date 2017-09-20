package ua.nure.tur.db.dao;

import ua.nure.tur.entities.Review;
import ua.nure.tur.exceptions.DataAccessException;

import java.util.List;

public interface ReviewDAO {

    List<Review> findForPeriodical(int id) throws DataAccessException;
}

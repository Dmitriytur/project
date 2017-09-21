package ua.nure.tur.services;

import ua.nure.tur.entities.Periodical;
import ua.nure.tur.exceptions.ServiceException;
import ua.nure.tur.viewmodels.PeriodicalDetailsViewModel;

import java.util.List;
import java.util.Map;

public interface PeriodicalService {

    List<Periodical> findAll() throws ServiceException;

    Periodical getById(Long id) throws ServiceException;

    Map<String, List<Periodical>> getPopularPeriodicalsByCategories(List<String> categories, int limit) throws ServiceException;

    PeriodicalDetailsViewModel getPeriodicalDetails(Long periodicalId) throws ServiceException;
}

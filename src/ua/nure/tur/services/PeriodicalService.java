package ua.nure.tur.services;

import ua.nure.tur.entities.Periodical;
import ua.nure.tur.exceptions.ServiceException;
import ua.nure.tur.viewmodels.PeriodicalDetailsViewModel;
import ua.nure.tur.viewmodels.SearchResultViewModel;

import java.util.List;
import java.util.Map;

public interface PeriodicalService {

    List<Periodical> findAll() throws ServiceException;

    Periodical getById(Long id) throws ServiceException;

    Map<String, List<Periodical>> getPopularPeriodicalsByCategories(List<String> categories, int limit) throws ServiceException;

    PeriodicalDetailsViewModel getPeriodicalDetails(Long periodicalId, int similarPeriodicalsLimit) throws ServiceException;

    SearchResultViewModel search(String name, String category, String sortBy, boolean desc, int limit, int offset) throws ServiceException;

    List<String> getAllCategories() throws ServiceException;
}

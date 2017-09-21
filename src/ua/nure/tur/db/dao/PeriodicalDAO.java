package ua.nure.tur.db.dao;

import ua.nure.tur.db.SearchSettings;
import ua.nure.tur.db.SearchSpecification;
import ua.nure.tur.entities.Periodical;
import ua.nure.tur.exceptions.DataAccessException;

import java.util.List;

public interface PeriodicalDAO {

    Periodical getById(Long id) throws DataAccessException;

    List<Periodical> find(SearchSettings searchSettings) throws DataAccessException;

    List<Integer> findAllCategories() throws DataAccessException;
}

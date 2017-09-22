package ua.nure.tur.db.dao;

import ua.nure.tur.db.SearchSettings;
import ua.nure.tur.db.SearchSettingsImpl;
import ua.nure.tur.entities.Periodical;
import ua.nure.tur.exceptions.DataAccessException;

import java.util.List;

public interface PeriodicalDAO {

    Periodical getById(Long id) throws DataAccessException;

    List<Periodical> find(SearchSettings searchSettings) throws DataAccessException;

    List<String> findAllCategories() throws DataAccessException;

    int getAmount(SearchSettingsImpl searchSettings) throws DataAccessException;

    void update(Periodical periodical);
}

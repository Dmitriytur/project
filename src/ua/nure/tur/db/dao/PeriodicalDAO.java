package ua.nure.tur.db.dao;

import ua.nure.tur.entities.Periodical;
import ua.nure.tur.exceptions.DataAccessException;

import java.util.List;

public interface PeriodicalDAO {

    List<Periodical> findAll() throws DataAccessException;

    Periodical getById(int id) throws DataAccessException;
}

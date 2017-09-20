package ua.nure.tur.services;

import ua.nure.tur.entities.Periodical;
import ua.nure.tur.exceptions.ServiceException;

import java.util.List;

public interface PeriodicalService {

    List<Periodical> findAll() throws ServiceException;

    Periodical getById(int id) throws ServiceException;

}

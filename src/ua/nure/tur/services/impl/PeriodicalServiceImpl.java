package ua.nure.tur.services.impl;

import ua.nure.tur.db.SearchSettingsImpl;
import ua.nure.tur.db.SearchSpecification;
import ua.nure.tur.db.dao.DAOManager;
import ua.nure.tur.db.dao.DAOManagerFactory;
import ua.nure.tur.db.dao.UserDAO;
import ua.nure.tur.db.spesifications.CategorySpecification;
import ua.nure.tur.db.spesifications.NameSpecification;
import ua.nure.tur.entities.Periodical;
import ua.nure.tur.entities.Review;
import ua.nure.tur.exceptions.DataAccessException;
import ua.nure.tur.exceptions.ServiceException;
import ua.nure.tur.services.PeriodicalService;
import ua.nure.tur.viewmodels.PeriodicalDetailsViewModel;
import ua.nure.tur.viewmodels.SearchResultViewModel;

import java.util.*;

import static ua.nure.tur.utils.ClosingUtils.close;

public class PeriodicalServiceImpl implements PeriodicalService {

    private DAOManagerFactory daoManagerFactory;

    public PeriodicalServiceImpl(DAOManagerFactory daoManagerFactory) {
        this.daoManagerFactory = daoManagerFactory;
    }

    @Override
    public List<Periodical> findAll() throws ServiceException {
        DAOManager daoManager = null;
        try {
            daoManager = daoManagerFactory.getDaoManager();
            return daoManager.getPeriodicalDAO().find(new SearchSettingsImpl());
        } catch (DataAccessException e) {
            throw new ServiceException("Cannot get all periodicals", e);
        } finally {
            close(daoManager);
        }
    }

    @Override
    public Periodical getById(Long id) throws ServiceException {
        DAOManager daoManager = null;
        try {
            daoManager = daoManagerFactory.getDaoManager();
            return daoManager.getPeriodicalDAO().getById(id);
        } catch (DataAccessException e) {
            throw new ServiceException("Cannot get periodical by id", e);
        } finally {
            close(daoManager);
        }
    }

    @Override
    public Map<String, List<Periodical>> getPopularPeriodicalsByCategories(List<String> categories, int limit) throws ServiceException {
        Map<String, List<Periodical>> result = new HashMap<>();

        List<SearchSpecification> specifications = new ArrayList<>();
        CategorySpecification categorySpecification = new CategorySpecification("");
        specifications.add(categorySpecification);
        SearchSettingsImpl searchSettings = new SearchSettingsImpl();
        searchSettings.setSearchSpecifications(specifications);
        searchSettings.setOrderSpecification("summary_rating");
        searchSettings.setDesc(true);
        searchSettings.setLimit(limit);

        DAOManager daoManager = null;
        try {
            daoManager = daoManagerFactory.getDaoManager();
            for (String category : categories) {
                categorySpecification.setCategory(category);
                List<Periodical> periodicals = daoManager.getPeriodicalDAO().find(searchSettings);
                result.put(category, periodicals);
            }

        } catch (DataAccessException e) {
            throw new ServiceException("Cannot get popular periodicals", e);
        } finally {
            close(daoManager);
        }
        return result;
    }

    @Override
    public PeriodicalDetailsViewModel getPeriodicalDetails(Long periodicalId, int similarPeriodicalsLimit) throws ServiceException {
        PeriodicalDetailsViewModel result = new PeriodicalDetailsViewModel();

        SearchSettingsImpl searchSettings = new SearchSettingsImpl();
        searchSettings.setLimit(similarPeriodicalsLimit + 1);

        DAOManager daoManager = null;
        try {
            daoManager = daoManagerFactory.getDaoManager();
            Periodical periodical = daoManager.getPeriodicalDAO().getById(periodicalId);
            result.setPeriodical(periodical);
            searchSettings.setSearchSpecifications(Arrays.asList(new CategorySpecification(periodical.getCategory())));
            result.setReviews(daoManager.getReviewDao().findForPeriodical(periodicalId));
            result.setSimilarPeriodicals(daoManager.getPeriodicalDAO().find(searchSettings));
            UserDAO userDAO = daoManager.getUserDao();
            for (Review review : result.getReviews()) {
                result.addUser(userDAO.findById(review.getUserId()));
            }
        } catch (DataAccessException e) {
            throw new ServiceException("Cannot get information for periodical details view mPodel", e);
        } finally {
            close(daoManager);
        }
        return result;
    }

    @Override
    public SearchResultViewModel search(String name, String category, String sortBy, boolean desc, int limit, int offset) throws ServiceException {
        SearchResultViewModel result = new SearchResultViewModel();
        SearchSettingsImpl searchSettings = new SearchSettingsImpl();

        if (name != null) {
            searchSettings.addSearchSpecification(new NameSpecification(name));
        }

        if (category != null && !category.isEmpty()) {
            searchSettings.addSearchSpecification(new CategorySpecification(category));
        }

        searchSettings.setOrderSpecification(sortBy);
        searchSettings.setDesc(desc);
        searchSettings.setLimit(limit);
        searchSettings.setOffset(offset);

        DAOManager daoManager = null;

        try {
            daoManager = daoManagerFactory.getDaoManager();
            int amount = daoManager.getPeriodicalDAO().getAmount(searchSettings);
            result.setAmount(amount);
            List<Periodical> periodicals = daoManager.getPeriodicalDAO().find(searchSettings);
            result.setPeriodicals(periodicals);
            return result;
        } catch (DataAccessException e) {
            throw new ServiceException("Cannot search periodicals", e);
        } finally {
            close(daoManager);
        }
    }

    @Override
    public List<String> getAllCategories() throws ServiceException {
        DAOManager daoManager = null;

        try {
            daoManager = daoManagerFactory.getDaoManager();
            return daoManager.getPeriodicalDAO().findAllCategories();
        } catch (DataAccessException e) {
            throw new ServiceException("Cannot get all categories", e);
        } finally {
            close(daoManager);
        }
    }

}

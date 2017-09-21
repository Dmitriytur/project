package ua.nure.tur.db.dao.mysql;

import ua.nure.tur.db.SearchSettings;
import ua.nure.tur.db.SearchSpecification;
import ua.nure.tur.db.dao.PeriodicalDAO;
import ua.nure.tur.entities.Periodical;
import ua.nure.tur.exceptions.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.tur.utils.ClosingUtils.close;

public class MysqlPeriodicalDAO implements PeriodicalDAO {

    private static final String SELECT_ITEMS = "select * from periodicals";

    private static final String SELECT_BY_ID  = "select * from periodicals where id=?";

    private static final String SELECT_CATEGORIES = "select * from categories";

    private Connection connection;

    public MysqlPeriodicalDAO(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Periodical getById(Long id) throws DataAccessException {
        Periodical periodical = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                periodical = extractPeriodical(resultSet);
            }
        } catch (SQLException e) {
            //TODO log exception
            throw new DataAccessException("Cannot get periodical by id form database", e);
        } finally {
            close(resultSet);
            close(statement);
        }
        return periodical;

    }


    @Override
    public List<Periodical> find(SearchSettings searchSettings) throws DataAccessException {
        List<Periodical> periodicals = new ArrayList<>();

        String query = searchSettings.buildQuery(SELECT_ITEMS);
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(query);
            searchSettings.prepareStatement(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                periodicals.add(extractPeriodical(resultSet));
            }
        } catch (SQLException e) {
            //TODO: log exceptions
            System.out.println(e.getMessage());
            throw new DataAccessException("Cannot obtain periodicals list", e);
        } finally {
            close(resultSet);
            close(statement);
        }
        return periodicals;
    }

    @Override
    public List<Integer> findAllCategories() throws DataAccessException {
        List<Integer> categories = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_CATEGORIES);
            while (resultSet.next()){
                categories.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e);
            //TODO log
            throw new DataAccessException("Cannot get all categories", e);
        } finally {
            close(resultSet);
            close(statement);
        }
        return categories;
    }


    private Periodical extractPeriodical(ResultSet resultSet) throws SQLException {
        Periodical periodical = new Periodical();
        periodical.setId(resultSet.getLong("id"));
        periodical.setName(resultSet.getString("name"));
        periodical.setDescription(resultSet.getString("description"));
        periodical.setPrice(resultSet.getDouble("price"));
        periodical.setPeriodicity(resultSet.getInt("periodicity"));
        periodical.setImages(resultSet.getInt("images"));
        periodical.setRating(resultSet.getDouble("rating"));
        periodical.setCategory(resultSet.getString("category"));
        return periodical;
    }
}

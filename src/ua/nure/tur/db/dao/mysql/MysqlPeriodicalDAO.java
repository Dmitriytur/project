package ua.nure.tur.db.dao.mysql;

import ua.nure.tur.db.dao.PeriodicalDAO;
import ua.nure.tur.entities.Periodical;
import ua.nure.tur.exceptions.DataAccessException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.tur.utils.ClosingUtils.close;

public class MysqlPeriodicalDAO implements PeriodicalDAO {

    private static final String SQL_GET_ALL_ITEMS = "select * from periodicals, categories where periodicals.category_id = categories.id";

    private Connection connection;

    public MysqlPeriodicalDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Periodical> findAll() throws DataAccessException {
        List<Periodical> periodicals = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_GET_ALL_ITEMS);
            while (resultSet.next()) {
                periodicals.add(extractPeriodical(resultSet));
            }
        } catch (SQLException e) {
            //TODO: log exceptions
            throw new DataAccessException("Cannot obtain periodicals list", e);
        } finally {
            close(resultSet);
            close(statement);
        }
        return periodicals;
    }

    private Periodical extractPeriodical(ResultSet resultSet) throws SQLException {
        Periodical periodical = new Periodical();
        periodical.setId(resultSet.getInt("periodicals.id"));
        periodical.setName(resultSet.getString("periodicals.name"));
        periodical.setDescription(resultSet.getString("description"));
        periodical.setPrice(resultSet.getDouble("price"));
        periodical.setPeriodicity(resultSet.getInt("periodicity"));
        periodical.setImage(resultSet.getString("image"));
        periodical.setRating(resultSet.getDouble("rating"));
        periodical.setCategory(resultSet.getString("categories.name"));
        return periodical;
    }
}

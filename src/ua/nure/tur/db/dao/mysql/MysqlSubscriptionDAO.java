package ua.nure.tur.db.dao.mysql;

import ua.nure.tur.db.dao.SubscriptionDAO;
import ua.nure.tur.entities.Subscription;
import ua.nure.tur.exceptions.DataAccessException;
import ua.nure.tur.viewmodels.UserSubscriptionViewModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.tur.utils.ClosingUtils.close;

public class MysqlSubscriptionDAO implements SubscriptionDAO {

    private static final String SELECT_USER_SUBSCRIPTIONS = "select * from subscriptions, periodicals " +
            "where  subscriptions.periodical_id=periodicals.id and user_id=?";

    private static final String INSERT_SUBSCRIPTION = "insert into subscriptions values (default, ?, ?, ?, ?)";

    private Connection connection;

    public MysqlSubscriptionDAO(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<UserSubscriptionViewModel> getUserSubscriptions(Long userId) throws DataAccessException {
        List<UserSubscriptionViewModel> result = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(SELECT_USER_SUBSCRIPTIONS);
            statement.setLong(1, userId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(extractUserSubscriptionViewModel(resultSet));
            }
        } catch (SQLException e) {
            //TODO log
            System.out.println(e);
            throw new DataAccessException("Cannot get user subscriptions", e);
        } finally {
            close(resultSet);
            close(statement);
        }
        return result;
    }

    @Override
    public void addSubscription(Subscription subscription) throws DataAccessException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(INSERT_SUBSCRIPTION);
            int i = 1;
            statement.setLong(i++, subscription.getUserId());
            statement.setLong(i++, subscription.getPeriodicalId());
            statement.setDate(i++, subscription.getStartDate());
            statement.setDate(i, subscription.getEndDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            //TODO log
            System.out.println(e.getMessage());
            throw new DataAccessException("Cannot insert subscription", e);
        } finally {
            close(statement);
        }
    }

    private UserSubscriptionViewModel extractUserSubscriptionViewModel(ResultSet resultSet) throws SQLException {
        UserSubscriptionViewModel model = new UserSubscriptionViewModel();
        model.setPeriodicalName(resultSet.getString("periodicals.name"));
        model.setSubscription(extractSubscription(resultSet));
        return model;
    }

    private Subscription extractSubscription(ResultSet resultSet) throws SQLException {
        Subscription subscription = new Subscription();
        subscription.setId(resultSet.getLong("subscriptions.id"));
        subscription.setUserId(resultSet.getLong("user_id"));
        subscription.setPeriodicalId(resultSet.getLong("periodical_id"));
        subscription.setStartDate(resultSet.getDate("start_date"));
        subscription.setEndDate(resultSet.getDate("end_date"));
        return subscription;
    }
}

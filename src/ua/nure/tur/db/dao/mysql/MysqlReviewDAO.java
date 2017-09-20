package ua.nure.tur.db.dao.mysql;

import ua.nure.tur.db.dao.ReviewDAO;
import ua.nure.tur.entities.Review;
import ua.nure.tur.exceptions.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlReviewDAO implements ReviewDAO {

    private static final String GET_BY_PERIODICAL_ID = "select * from reviews where periodical_id=?";

    private Connection connection;

    public MysqlReviewDAO(Connection connection) {
        this.connection = connection;
    }

    private Review extractReview(ResultSet resultSet) throws SQLException {
        Review review = new Review();
        review.setId(resultSet.getInt("id"));
        review.setScore(resultSet.getInt("score"));
        review.setMessage(resultSet.getString("message"));
        review.setCreationDate(resultSet.getDate("creation_date"));
        review.setUserId(resultSet.getInt("user_id"));
        review.setPeriodicalId(resultSet.getInt("periodical_id"));
        return review;
    }

    @Override
    public List<Review> findForPeriodical(int id) throws DataAccessException {
        List<Review> reviews = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(GET_BY_PERIODICAL_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                reviews.add(extractReview(resultSet));
            }
        } catch (SQLException e) {
            //TODO log
            System.out.println(e.getMessage());
            throw new DataAccessException("Failed getting reviews for periodical", e);
        }
        return reviews;

    }
}

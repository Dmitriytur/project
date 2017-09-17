package ua.nure.tur.db.dao.mysql;

import ua.nure.tur.db.dao.ReviewDAO;
import ua.nure.tur.entities.Review;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlReviewDAO implements ReviewDAO {

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
}

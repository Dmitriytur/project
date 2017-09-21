package ua.nure.tur.db.spesifications;

import ua.nure.tur.db.SearchSpecification;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CategorySpecification implements SearchSpecification {


    private String category;

    public CategorySpecification(String category) {
        this.category = category;
    }

    @Override
    public String getCondition() {
        return "category=?";

    }

    @Override
    public void prepareStatement(PreparedStatement statement, int index) throws SQLException {
        statement.setString(index, category);
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

package ua.nure.tur.db.spesifications;

import ua.nure.tur.db.SearchSpecification;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NameSpecification implements SearchSpecification {

    private String name;

    public NameSpecification(String name) {
        this.name = String.format("%%%s%%", name);
    }

    @Override
    public String getCondition() {
        return "name LIKE ?";
    }

    @Override
    public void prepareStatement(PreparedStatement statement, int index) throws SQLException {
        statement.setString(index, name);
    }
}

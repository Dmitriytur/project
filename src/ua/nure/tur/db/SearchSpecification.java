package ua.nure.tur.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SearchSpecification {

    String getCondition();

    void prepareStatement(PreparedStatement statement, int index) throws SQLException;
}

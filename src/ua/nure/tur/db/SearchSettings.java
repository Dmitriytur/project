package ua.nure.tur.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SearchSettings {

    String buildQueryConditions();

    String buildFilterPart();

    void prepareStatement(PreparedStatement statement) throws SQLException;
}

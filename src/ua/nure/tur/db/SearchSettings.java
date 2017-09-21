package ua.nure.tur.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SearchSettings {

    String buildQueryConditions();

    void prepareStatement(PreparedStatement statement) throws SQLException;
}

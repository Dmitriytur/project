package ua.nure.tur.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SearchSettings {

    String buildQuery(String baseQuery);

    void prepareStatement(PreparedStatement statement) throws SQLException;
}

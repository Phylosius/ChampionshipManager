package school.hei.championshipmanager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultSetHandler {
    /**
     * Handle a {@link ResultSet}
     *
     * @param resultSet the {@link ResultSet}
     * @throws SQLException
     * @returna amount of given element by the {@link ResultSet}
     * */
    int execute(ResultSet resultSet) throws SQLException;
}

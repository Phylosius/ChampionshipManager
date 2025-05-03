package school.hei.championshipmanager.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface PreparedStatementHandler {
    /**
     * Executes the given {@link PreparedStatement}
     *
     * @param preparedStatement the {@link PreparedStatement} created from a sql query
     * @throws SQLException
     * @return amount of affected elements by the query
     * */
    Integer execute(PreparedStatement preparedStatement) throws SQLException;
}

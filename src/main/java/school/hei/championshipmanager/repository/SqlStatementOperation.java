package school.hei.championshipmanager.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class SqlStatementOperation {

    private final DataSource dataSource;

    public Integer executeQuery(
            String sql, List<?> params,
            Integer page, Integer pageSize,
            ResultSetHandler resultSetHandler
    )
    {
        return executePreparedStatement(sql, params, page, pageSize, preparedStatement -> {
           return resultSetHandler.execute(preparedStatement.executeQuery());
        });
    }

    public Integer executeUpdate(String sql, List<?> params) {
        return executePreparedStatement(sql, params, null, null, PreparedStatement::executeUpdate);
    }

    public Integer executePreparedStatement(
            String sql, List<?> params,
            Integer page, Integer pageSize,
            PreparedStatementHandler preparedStatementHandler
    )
    {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {

            if (params != null) {
                params = new ArrayList<>(params);

                for (int i = 1; i < params.size() + 1; i++) {
                    preparedStatement.setObject(i, params.get(i - 1));
                }
            }

            if (page != null && pageSize != null) {
                sql = sql + String.format(" LIMIT %s OFFSET %s", pageSize, page * (pageSize - 1));
            }

            return preparedStatementHandler.execute(preparedStatement);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

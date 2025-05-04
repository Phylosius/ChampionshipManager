package school.hei.championshipmanager.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import school.hei.championshipmanager.exeptions.EntityAlreadyExistException;
import school.hei.championshipmanager.mappers.ModelRepositoryMapper;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class BaseRepo<E> {

    private final SqlStatementOperation sqlStatementOperation;

    public List<E> getAll(String selectSql, List<?> params, Integer page, Integer pageSize, ModelRepositoryMapper<E> modelRepositoryMapper) {
        List<E> result = new ArrayList<>();

        sqlStatementOperation.executeQuery(selectSql, params, page, pageSize, resultSet -> {
           int retrieved = 0;
           while (resultSet.next()) {
               retrieved++;
               result.add(modelRepositoryMapper.toModel(resultSet));
           }

           return retrieved;
        });

        return result;
    }

    public int update(String updateSql, E entity, ModelRepositoryMapper<E> modelRepositoryMapper) {
        return sqlStatementOperation.executeUpdate(updateSql, modelRepositoryMapper.toUpdateParams(entity));
    }

    public int add(String insertionSql, E entity, ModelRepositoryMapper<E> modelRepositoryMapper) throws EntityAlreadyExistException {
        return sqlStatementOperation.executeUpdate(insertionSql, modelRepositoryMapper.toCreationParams(entity));
    }

    public int delete(String deletionSql, String id) {
        return sqlStatementOperation.executeUpdate(deletionSql, List.of(id));
    }

    public boolean isExists(String selectSql, String id) {

        return sqlStatementOperation.executeQuery(selectSql, List.of(id), null, null,
                resultSet -> {
                    if (resultSet.next()) {
                        return 1;
                    }
                    return 0;
                }) == 1;
    }
}

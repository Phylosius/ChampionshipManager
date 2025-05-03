package school.hei.championshipmanager.mappers;

import java.sql.ResultSet;
import java.util.List;

public interface ModelRepositoryMapper<E> {
    List<?> toCreationParams(E entity);
    List<?> toUpdateParams(E entity);
    E toModel(ResultSet rs);
}

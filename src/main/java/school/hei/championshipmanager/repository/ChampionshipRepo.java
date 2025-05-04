package school.hei.championshipmanager.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import school.hei.championshipmanager.exeptions.EntityAlreadyExistException;
import school.hei.championshipmanager.exeptions.EntityNotFoundException;
import school.hei.championshipmanager.mappers.ChampionshipMapper;
import school.hei.championshipmanager.model.Championship;

import java.util.List;

@AllArgsConstructor
@Repository
public class ChampionshipRepo implements EntityRepo<Championship, String> {

    private final ChampionshipMapper championshipMapper;
    private final BaseRepo<Championship> baseRepo;

    @Override
    public List<Championship> getAll(Integer page, Integer pageSize) {
        return baseRepo.getAll("""
                SELECT
                id, name, country_id
                FROM
                championship
                """, null, page, pageSize, championshipMapper);
    }

    @Override
    public Championship getById(String id) throws EntityNotFoundException {
        return baseRepo.getAll("""
                SELECT
                id, name, country_id
                FROM
                championship
                WHERE id = ?
                """, List.of(id), null, null, championshipMapper).getFirst();
    }

    @Override
    public int add(Championship entity) throws EntityAlreadyExistException {
        if (exists(entity.getId())) {
            throw new EntityAlreadyExistException("Championship with id " + entity.getId() + " already exists");
        }

        return baseRepo.add("""
                INSERT INTO
                championship
                (id, name, country_id)
                VALUES
                (?, ?, ?)
                """, entity, championshipMapper);
    }

    @Override
    public int update(Championship entity) {
        return baseRepo.update("""
                UPDATE
                championship
                SET name = ?, country_id = ?
                WHERE id = ?
                """, entity, championshipMapper);
    }

    @Override
    public int delete(String id) throws EntityNotFoundException {
        return baseRepo.delete("DELETE FROM championship WHERE id = ?", id);
    }

    public boolean exists(String id) {
        return baseRepo.isExists("SELECT COUNT(*) FROM championship WHERE id = ?", id);
    }
}

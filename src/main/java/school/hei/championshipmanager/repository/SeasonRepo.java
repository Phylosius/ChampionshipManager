package school.hei.championshipmanager.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import school.hei.championshipmanager.exeptions.EntityAlreadyExistException;
import school.hei.championshipmanager.exeptions.EntityNotFoundException;
import school.hei.championshipmanager.mappers.SeasonMapper;
import school.hei.championshipmanager.model.Season;

import java.util.List;

@AllArgsConstructor
@Repository
public class SeasonRepo implements EntityRepo<Season, String> {
    private final SeasonMapper seasonMapper;
    private final BaseRepo<Season> baseRepo;

    @Override
    public List<Season> getAll(Integer page, Integer pageSize) {
        return baseRepo.getAll("""
                SELECT
                id, year, status
                FROM
                season
                """, null, null, null, seasonMapper);
    }

    @Override
    public Season getById(String id) throws EntityNotFoundException {
        return baseRepo.getAll("""
                SELECT
                id, year, status
                FROM
                season
                WHERE id = ?
                """, List.of(id), null, null, seasonMapper).getFirst();
    }

    @Override
    public int add(Season season) throws EntityAlreadyExistException {
        if (exists(season.getId())) {
            throw new EntityAlreadyExistException("Season with id " + season.getId() + " already exists");
        }

        return baseRepo.add("""
                INSERT INTO season
                (id, year, status)
                VALUES
                (?, ?, ?::public."EVENT_STATUS")
                """, season, seasonMapper);
    }

    @Override
    public int update(Season season) {
        return baseRepo.update("""
                UPDATE season
                SET year = ?, status = ?::public."EVENT_STATUS"
                WHERE id = ?
                """, season, seasonMapper);
    }

    @Override
    public int delete(String id) throws EntityNotFoundException {
        return baseRepo.delete("DELETE FROM season WHERE id = ?", id);
    }

    public boolean exists(String id) {
        return baseRepo.isExists("SELECT COUNT(*) FROM season WHERE id = ?", id);
    }
}

package school.hei.championshipmanager.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import school.hei.championshipmanager.exeptions.EntityAlreadyExistException;
import school.hei.championshipmanager.exeptions.EntityNotFoundException;
import school.hei.championshipmanager.mappers.ClubMapper;
import school.hei.championshipmanager.model.Club;

import java.util.List;

@AllArgsConstructor
@Repository
public class ClubRepo implements EntityRepo<Club, String> {

    private final ClubMapper clubMapper;
    private final BaseRepo<Club> baseRepo;

    @Override
    public List<Club> getAll(Integer page, Integer pageSize) {
        return getAllBy(null, null, page, pageSize);
    }

    @Override
    public Club getById(String id) throws EntityNotFoundException {
        return getAllBy("id = ?", List.of(id), null, null).getFirst();
    }

    public List<Club> getAllBy(String conditionSql, List<?> sqlParams, Integer page, Integer pageSize) {
        return baseRepo.getAllBy("""
                SELECT
                id, name, creation_year, acronym, stadium_name, championship_id
                FROM
                club
                """, conditionSql, sqlParams, page, pageSize, clubMapper);
    }

    public int save(Club club) {
        if (exists(club)) {
            return update(club);
        } else {
            return add(club);
        }
    }

    @Override
    public int add(Club club) throws EntityAlreadyExistException {
        return baseRepo.add("""
                INSERT INTO
                club
                (id, name, creation_year, acronym, stadium_name, championship_id)
                VALUES
                (?, ?, ?, ?, ?, ?)
                """, club, clubMapper);
    }

    @Override
    public int update(Club club) {
        return baseRepo.update("""
                UPDATE
                club
                SET
                name = ?, creation_year = ?, acronym = ?, stadium_name = ?, championship_id = ?
                WHERE
                id = ?
                """, club, clubMapper);
    }

    @Override
    public int delete(String id) throws EntityNotFoundException {
        return baseRepo.delete("""
                DELETE FROM club WHERE id = ?
                """, id);
    }

    public Boolean exists(Club club) {
        return baseRepo.isExists("SELECT id FROM club WHERE id = ?", club.getId());
    }
}

package school.hei.championshipmanager.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import school.hei.championshipmanager.exeptions.EntityAlreadyExistException;
import school.hei.championshipmanager.exeptions.EntityNotFoundException;
import school.hei.championshipmanager.mappers.CoachMapper;
import school.hei.championshipmanager.model.Coach;

import java.util.List;

@AllArgsConstructor
@Repository
public class CoachRepo implements EntityRepo<Coach, String> {
    private final CoachMapper coachMapper;
    private final BaseRepo<Coach> baseRepo;

    @Override
    public List<Coach> getAll(Integer page, Integer pageSize) {
        return getAllBy(null, null, page, pageSize);
    }

    public Coach getByClubId(String id) {
        return getAllBy("club_id = ?", List.of(id), null, null).getFirst();
    }

    @Override
    public Coach getById(String id) throws EntityNotFoundException {
        return getAllBy("id = ?", List.of(id), null, null).getFirst();
    }

    public List<Coach> getAllBy(String conditionSql, List<?> sqlParams, Integer page, Integer pageSize) {
        return baseRepo.getAllBy("""
                SELECT
                id, name, nationality, club_id
                FROM
                coach
                """, conditionSql, sqlParams, page, pageSize, coachMapper);
    }

    @Override
    public int add(Coach coach) throws EntityAlreadyExistException {
        if (exists(coach.getId())) {
            throw new EntityAlreadyExistException("Coach with id " + coach.getId() + " already exists");
        }

        return baseRepo.add("""
                INSERT INTO coach
                (id, name, nationality, club_id)
                VALUES
                (?, ?, ?, ?)
                """, coach, coachMapper);
    }

    @Override
    public int update(Coach coach) {
        return baseRepo.update("""
                UPDATE coach
                SET name = ?, nationality = ?, club_id = ?
                WHERE id = ?
                """, coach, coachMapper);
    }

    @Override
    public int delete(String id) throws EntityNotFoundException {
        return baseRepo.delete("DELETE FROM coach WHERE id = ?", id);
    }

    public boolean exists(String id) {
        return baseRepo.isExists("SELECT id FROM coach WHERE id = ?", id);
    }
}

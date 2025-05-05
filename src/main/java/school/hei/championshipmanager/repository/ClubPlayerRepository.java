package school.hei.championshipmanager.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import school.hei.championshipmanager.exeptions.EntityAlreadyExistException;
import school.hei.championshipmanager.exeptions.EntityNotFoundException;
import school.hei.championshipmanager.mappers.ClubPlayerMapper;
import school.hei.championshipmanager.model.ClubPlayer;

import java.util.List;

@AllArgsConstructor
@Repository
public class ClubPlayerRepository implements EntityRepo<ClubPlayer, String> {

    private final ClubPlayerMapper mapper;
    private final BaseRepo<ClubPlayer> baseRepo;

    @Override
    public List<ClubPlayer> getAll(Integer page, Integer pageSize) {
        return getAllBy(null, null, page, pageSize);
    }

    @Override
    public ClubPlayer getById(String roleId) throws EntityNotFoundException {
        return getAllBy("r.id = ?", List.of(roleId), null, null).getFirst();
    }

    public List<ClubPlayer> getAllBy(String conditionSql, List<?> sqlParams, Integer page, Integer pageSize) {
        return baseRepo.getAllBy("""
                SELECT
                r.id AS role_id,
                r.club_id AS club_id,
                r.player_id AS player_id,
                p.name AS player_name,
                p.age AS player_age,
                p.country_id AS country_id,
                r.player_number AS player_number,
                r.player_position AS player_position,
                r.is_active AS is_active
                
                FROM player_role r
                JOIN player p
                    ON p.id = r.player_id
                """, conditionSql, sqlParams, page, pageSize, mapper);
    }

    @Override
    public int add(ClubPlayer clubPlayer) throws EntityAlreadyExistException {
        return baseRepo.add("""
                INSERT INTO
                player_role
                (id, club_id, player_id, player_number, player_position, is_active)
                VALUES
                (?, ?, ?, ?, ?, ?)
                """, clubPlayer, mapper);
    }

    @Override
    public int update(ClubPlayer clubPlayer) {
        return baseRepo.add("""
                UPDATE
                player_role
                SET
                club_id = ?, player_id = ?, player_number = ?, player_position = ?, is_active = ?
                WHERE club_id = ? AND player_id = ?
                """, clubPlayer, mapper);
    }

    @Override
    public int delete(String id) throws EntityNotFoundException {
        return baseRepo.delete("DELETE FROM player_role WHERE id = ?", id);
    }
}

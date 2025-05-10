package school.hei.championshipmanager.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import school.hei.championshipmanager.exeptions.EntityAlreadyExistException;
import school.hei.championshipmanager.exeptions.EntityNotFoundException;
import school.hei.championshipmanager.mappers.ClubPlayerMapper;
import school.hei.championshipmanager.model.ClubPlayer;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class ClubPlayerRepository implements EntityRepo<ClubPlayer, String> {

    private final ClubPlayerMapper mapper;
    private final BaseRepo<ClubPlayer> baseRepo;
    private final PlayerRepo playerRepo;

    @Override
    public List<ClubPlayer> getAll(Integer page, Integer pageSize) {
        return getAllBy(null, null, page, pageSize);
    }

    @Override
    public ClubPlayer getById(String roleId) throws EntityNotFoundException {
        return getAllBy("r.id = ?", List.of(roleId), null, null).getFirst();
    }

    public List<ClubPlayer> getAllByClubId(String clubId, Integer page, Integer pageSize) {
        return getAllBy("r.club_id = ?", List.of(clubId), page, pageSize);
    }

    public List<ClubPlayer> getAllFiltered(String nameContaining, String clubNameContaining, Integer ageMin, Integer ageMax, Integer page, Integer pageSize) {
        StringBuilder conditionSql = null;
        List<Object> sqlParams = null;

        if (nameContaining != null || clubNameContaining != null || ageMin != null || ageMax != null || page != null || pageSize != null) {
            conditionSql = new StringBuilder("1=1");
            sqlParams = new ArrayList<>();

            if (nameContaining != null) {
                conditionSql.append(" AND p.name ILIKE '%' || ? || '%'");
                sqlParams.add(nameContaining);
            }

            if (clubNameContaining != null) {
                conditionSql.append(" AND club.name ILIKE '%' || ? || '%'");
                sqlParams.add(clubNameContaining);
            }

            if (ageMin != null) {
                conditionSql.append(" AND p.age >= ?");
                sqlParams.add(ageMin);
            }

            if (ageMax != null) {
                conditionSql.append(" AND p.age <= ?");
                sqlParams.add(ageMax);
            }
        }

        return getAllBy(conditionSql != null ? conditionSql.toString() : null, sqlParams, page, pageSize);
    }

    public List<ClubPlayer> getAllBy(String conditionSql, List<?> sqlParams, Integer page, Integer pageSize) {
        return baseRepo.getAllBy("""
                SELECT
                r.id AS role_id,
                r.club_id AS club_id,
                r.player_id AS player_id,
                p.name AS player_name,
                p.age AS player_age,
                p.nationality AS country_id,
                r.player_number AS player_number,
                r.player_position AS player_position,
                r.is_active AS is_active
                
                FROM player_role r
                JOIN player p
                    ON p.id = r.player_id
                JOIN club ON club.id = r.club_id
                """, conditionSql, sqlParams, page, pageSize, mapper);
    }

    public int save(ClubPlayer clubPlayer) {
        if (isExists(clubPlayer)) {
            return update(clubPlayer);
        } else {
            return add(clubPlayer);
        }
    }

    @Override
    public int add(ClubPlayer clubPlayer) throws EntityAlreadyExistException {
        playerRepo.save(clubPlayer);
        return baseRepo.add("""
                INSERT INTO
                player_role
                (id, club_id, player_id, player_number, player_position, is_active)
                VALUES
                (?, ?, ?, ?, ?::"PLAYER_POSITION", ?)
                """, clubPlayer, mapper);
    }

    @Override
    public int update(ClubPlayer clubPlayer) {
        playerRepo.save(clubPlayer);
        return baseRepo.update("""
                UPDATE
                player_role
                SET
                club_id = ?, player_id = ?, player_number = ?, player_position = ?::"PLAYER_POSITION", is_active = ?
                WHERE player_id = ? AND club_id = ?
                """, clubPlayer, mapper);
    }

    @Override
    public int delete(String id) throws EntityNotFoundException {
        return baseRepo.delete("DELETE FROM player_role WHERE id = ?", id);
    }

    public int deleteAllByClubId(String clubId) {
        return baseRepo.delete("DELETE FROM player_role WHERE club_id = ?", clubId);
    }

    public Boolean isExists(ClubPlayer player) throws EntityNotFoundException {
        return baseRepo.isExists("SELECT id FROM player_role WHERE player_id = ?", player.getId());
    }
}

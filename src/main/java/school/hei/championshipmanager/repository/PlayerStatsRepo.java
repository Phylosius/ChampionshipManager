package school.hei.championshipmanager.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import school.hei.championshipmanager.exeptions.EntityAlreadyExistException;
import school.hei.championshipmanager.exeptions.EntityNotFoundException;
import school.hei.championshipmanager.mappers.PlayerStatsMapper;
import school.hei.championshipmanager.model.PlayerStats;

import java.util.List;

@AllArgsConstructor
@Repository
public class PlayerStatsRepo implements EntityRepo<PlayerStats, String> {
    private final PlayerStatsMapper statsMapper;
    private final BaseRepo<PlayerStats> baseRepo;
    private final PlayerStatsMapper playerStatsMapper;

    @Override
    public List<PlayerStats> getAll(Integer page, Integer pageSize) {
        return getAllBy(null, null, page, pageSize);
    }

    @Override
    public PlayerStats getById(String id) throws EntityNotFoundException {
        return getAllBy("id = ?", List.of(id), null, null).getFirst();
    }

    public List<PlayerStats> getAllBy(String conditionSql, List<?> sqlParams, Integer page, Integer pageSize) {
        return baseRepo.getAllBy("""
                SELECT
                id, player_id, match_id, playing_time
                FROM
                player_stats
                """, conditionSql, sqlParams, page, pageSize, statsMapper);
    }

    @Override
    public int add(PlayerStats stats) throws EntityAlreadyExistException {
        if (exists(stats.getId())) {
            throw new EntityAlreadyExistException("PlayerStats with id " + stats.getId() + " already exists");
        }

        return baseRepo.add("""
                INSERT INTO player_stats
                (id, player_id, match_id, playing_time)
                VALUES
                (?, ?, ?, ?)
                """, stats, statsMapper);
    }

    @Override
    public int update(PlayerStats stats) {
        return baseRepo.update("""
                UPDATE player_stats
                SET player_id = ?, match_id = ?, minute_of_goal = ?, own_goal = ?
                WHERE id = ?
                """, stats, playerStatsMapper);
    }

    @Override
    public int delete(String id) throws EntityNotFoundException {
        return baseRepo.delete("DELETE FROM player_stats WHERE id = ?", id);
    }

    public boolean exists(String id) {
        return baseRepo.isExists("SELECT COUNT(*) FROM player_stats WHERE id = ?", id);
    }
}

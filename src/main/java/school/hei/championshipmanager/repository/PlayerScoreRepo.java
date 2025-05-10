package school.hei.championshipmanager.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import school.hei.championshipmanager.exeptions.EntityAlreadyExistException;
import school.hei.championshipmanager.exeptions.EntityNotFoundException;
import school.hei.championshipmanager.mappers.PlayerScoreMapper;
import school.hei.championshipmanager.model.PlayerScore;

import java.util.List;

@AllArgsConstructor
@Repository
public class PlayerScoreRepo implements EntityRepo<PlayerScore, String> {
    private final PlayerScoreMapper playerScoreMapper;
    private final BaseRepo<PlayerScore> baseRepo;

    @Override
    public List<PlayerScore> getAll(Integer page, Integer pageSize) {
        return getAllBy(null, null, page, pageSize);
    }

    @Override
    public PlayerScore getById(String id) throws EntityNotFoundException {
        return getAllBy("id = ?", List.of(id), null, null).getFirst();
    }

    public List<PlayerScore> getAllByPlayerIdAndMatchId(String playerId, String matchId, Integer page, Integer pageSize) {
        return getAllBy("player_id = ? AND match_id = ?", List.of(playerId, matchId), page, pageSize);
    }

    public List<PlayerScore> getAllBy(String conditionSql, List<?> sqlParams, Integer page, Integer pageSize) {
        return baseRepo.getAllBy("""
                SELECT
                id, player_id, match_id, minute_of_goal, own_goal
                FROM
                score
                """, conditionSql, sqlParams, page, pageSize, playerScoreMapper);
    }

    @Override
    public int add(PlayerScore playerScore) throws EntityAlreadyExistException {
        if (exists(playerScore.getId())) {
            throw new EntityAlreadyExistException("PlayerScore with id " + playerScore.getId() + " already exists");
        }

        return baseRepo.add("""
                INSERT INTO score
                (id, player_id, match_id, minute_of_goal, own_goal)
                VALUES
                (?, ?, ?, ?, ?)
                """, playerScore, playerScoreMapper);
    }

    @Override
    public int update(PlayerScore playerScore) {
        return baseRepo.update("""
                UPDATE score
                SET player_id = ?, match_id = ?, minute_of_goal = ?, own_goal = ?
                WHERE id = ?
                """, playerScore, playerScoreMapper);
    }

    @Override
    public int delete(String id) throws EntityNotFoundException {
        return baseRepo.delete("DELETE FROM score WHERE id = ?", id);
    }

    public boolean exists(String id) {
        return baseRepo.isExists("SELECT id FROM score WHERE id = ?", id);
    }
}

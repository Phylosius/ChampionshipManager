package school.hei.championshipmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.hei.championshipmanager.repository.PlayerScoreRepo;

import java.time.Duration;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerStats {
    private String id;
    private String playerId;
    private String matchId;
    private Duration playingTime;

    public List<PlayerScore> getScores(PlayerScoreRepo playerScoreRepo) {
        return playerScoreRepo.getAllByPlayerIdAndMatchId(playerId, matchId, null, null);
    }
}

package school.hei.championshipmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.hei.championshipmanager.repository.PlayerScoreRepo;
import school.hei.championshipmanager.repository.PlayerStatsRepo;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Player {
    private String id;
    private String name;
    private Integer age;
    private Country country;

    public List<PlayerStats> getStats(PlayerStatsRepo playerStatsRepo, Integer seasonYear, String matchId) {
        return playerStatsRepo.getAll(id, seasonYear, matchId);
    }

    public Integer getScoredGoals(PlayerStatsRepo playerStatsRepo, PlayerScoreRepo playerScoreRepo,
                                  Integer seasonYear, String matchId, Boolean excludeOwned) {
        AtomicInteger scoredGoals = new AtomicInteger(0);
        List<PlayerStats> playerStats = getStats(playerStatsRepo, seasonYear, matchId);

        playerStats.forEach(s -> {
            s.getScores(playerScoreRepo).forEach(score -> {
                if (excludeOwned) {
                    if (!score.getOwnGoal()) {
                        scoredGoals.incrementAndGet();
                    }
                } else {
                    scoredGoals.incrementAndGet();
                }
            });
        });

        return scoredGoals.get();
    }
}

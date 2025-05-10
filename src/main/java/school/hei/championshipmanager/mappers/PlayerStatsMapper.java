package school.hei.championshipmanager.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import school.hei.championshipmanager.dto.PlayerStatisticsRest;
import school.hei.championshipmanager.dto.PlayingTimeRest;
import school.hei.championshipmanager.dto.ScorerRest;
import school.hei.championshipmanager.enums.DurationUnit;
import school.hei.championshipmanager.model.PlayerScore;
import school.hei.championshipmanager.model.PlayerStats;
import school.hei.championshipmanager.repository.PlayerScoreRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@AllArgsConstructor
@Component
public class PlayerStatsMapper implements ModelRepositoryMapper<PlayerStats> {

    private final PlayerScoreRepo playerScoreRepo;
    private final PlayerScoreMapper playerScoreMapper;

    @Override
    public List<?> toCreationParams(PlayerStats stats) {
        return List.of(stats.getId(),
                stats.getPlayerId(), stats.getMatchId(),
                stats.getPlayingTime().toSeconds());
    }

    @Override
    public List<?> toUpdateParams(PlayerStats stats) {
        return List.of(stats.getPlayerId(), stats.getMatchId(),
                stats.getPlayingTime().toSeconds(),
                stats.getId());
    }

    @Override
    public PlayerStats toModel(ResultSet rs) {
        try {
            PlayerStats stats = new PlayerStats();

            stats.setId(rs.getString("id"));
            stats.setPlayerId(rs.getString("player_id"));
            stats.setMatchId(rs.getString("match_id"));
            stats.setPlayingTime(Duration.ofSeconds(rs.getLong("playing_time")));

            return stats;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PlayerStatisticsRest toDTO(List<PlayerStats> stats, DurationUnit durationUnit) {
        PlayerStatisticsRest dto = new PlayerStatisticsRest();

        PlayingTimeRest playingTimeRest = new PlayingTimeRest();

        AtomicInteger scoredGoals = new AtomicInteger(0);
        AtomicLong playingTimeValue = new AtomicLong(0);

        stats.forEach(stat -> {
            playingTimeValue.set(playingTimeValue.get() + switch (durationUnit) {
                case SECOND -> stat.getPlayingTime().toSeconds();
                case MINUTE -> stat.getPlayingTime().toMinutes();
                case HOUR -> stat.getPlayingTime().toHours();
            });

            List<ScorerRest> scores = stat.getScores(playerScoreRepo).stream()
                    .filter(s -> !s.getOwnGoal())
                    .map(playerScoreMapper::toDTO).toList();
            scoredGoals.set(
                    scoredGoals.get() + scores.size()
            );
        });

        playingTimeRest.setDurationUnit(durationUnit);
        playingTimeRest.setValue(playingTimeValue.get());

        dto.setPlayingTime(playingTimeRest);

        dto.setScoredGoals(scoredGoals.get());

        return dto;
    }
}

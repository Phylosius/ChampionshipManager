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

    public PlayerStatisticsRest toDTO(PlayerStats stats, DurationUnit durationUnit) {
        PlayerStatisticsRest dto = new PlayerStatisticsRest();

        PlayingTimeRest playingTimeRest = new PlayingTimeRest();
        playingTimeRest.setValue(switch (durationUnit){
            case SECOND -> stats.getPlayingTime().toSeconds();
            case MINUTE -> stats.getPlayingTime().toMinutes();
            case HOUR -> stats.getPlayingTime().toHours();
        });
        playingTimeRest.setDurationUnit(durationUnit);

        List<ScorerRest> scores = stats.getScores(playerScoreRepo).stream()
                .filter(s -> !s.getOwnGoal())
                .map(playerScoreMapper::toDTO).toList();

        dto.setPlayingTime(playingTimeRest);
        dto.setScoredGoals(scores.size());

        return dto;
    }
}

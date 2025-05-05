package school.hei.championshipmanager.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import school.hei.championshipmanager.model.PlayerStats;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;

@AllArgsConstructor
@Component
public class PlayerStatsMapper implements ModelRepositoryMapper<PlayerStats> {

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
}

package school.hei.championshipmanager.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import school.hei.championshipmanager.model.PlayerScore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;

@AllArgsConstructor
@Component
public class PlayerScoreMapper implements ModelRepositoryMapper<PlayerScore> {

    @Override
    public List<?> toCreationParams(PlayerScore playerScore) {
        return List.of(playerScore.getId(),
                playerScore.getPlayerId(), playerScore.getMatchId(),
                playerScore.getMinuteOfGoal().toMinutes(), playerScore.getOwnGoal());
    }

    @Override
    public List<?> toUpdateParams(PlayerScore playerScore) {
        return List.of(playerScore.getPlayerId(), playerScore.getMatchId(),
                playerScore.getMinuteOfGoal().toMinutes(), playerScore.getOwnGoal(),
                playerScore.getId());
    }

    @Override
    public PlayerScore toModel(ResultSet rs) {
        try {
            PlayerScore playerScore = new PlayerScore();

            playerScore.setId(rs.getString("id"));
            playerScore.setPlayerId(rs.getString("player_id"));
            playerScore.setMatchId(rs.getString("match_id"));
            playerScore.setMinuteOfGoal(Duration.ofMinutes(rs.getLong("minute_of_goal")));
            playerScore.setOwnGoal(rs.getBoolean("own_goal"));

            return playerScore;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

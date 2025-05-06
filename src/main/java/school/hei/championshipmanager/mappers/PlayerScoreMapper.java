package school.hei.championshipmanager.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import school.hei.championshipmanager.dto.AddGoalRest;
import school.hei.championshipmanager.dto.ScorerRest;
import school.hei.championshipmanager.model.Player;
import school.hei.championshipmanager.model.PlayerScore;
import school.hei.championshipmanager.repository.ClubPlayerRepository;
import school.hei.championshipmanager.repository.PlayerRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Component
public class PlayerScoreMapper implements ModelRepositoryMapper<PlayerScore> {

    private final PlayerRepo playerRepo;
    private final ClubPlayerRepository clubPlayerRepository;

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

    public PlayerScore toModel(String matchId, String playerId, AddGoalRest addGoal) {
        PlayerScore score = new PlayerScore();

        score.setPlayerId(playerId);
        score.setMatchId(matchId);
        score.setId(UUID.randomUUID().toString());
        score.setOwnGoal(true);
        score.setMinuteOfGoal(Duration.ofMinutes(addGoal.getMinuteOfGoal()));

        return score;
    }

    public ScorerRest toDTO(PlayerScore score) {
        ScorerRest dto = new ScorerRest();
        Player player = score.getPlayer(playerRepo);

        dto.setId(player.getId());
        dto.setName(player.getName());
        dto.setMinuteOfGoal((int) score.getMinuteOfGoal().toMinutes());
        dto.setOwnGoal(score.getOwnGoal());
        dto.setNumber(score.getClubPlayer(clubPlayerRepository).getNumber());

        return dto;
    }
}

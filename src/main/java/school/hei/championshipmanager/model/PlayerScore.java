package school.hei.championshipmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.hei.championshipmanager.repository.ClubPlayerRepository;
import school.hei.championshipmanager.repository.ClubRepo;
import school.hei.championshipmanager.repository.PlayerRepo;

import java.time.Duration;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerScore {
    private String id;
    private String playerId;
    private String matchId;
    private Duration minuteOfGoal;
    private Boolean ownGoal;

    public Player getPlayer(PlayerRepo playerRepo) {
        return playerRepo.getById(playerId);
    }

    public ClubPlayer getClubPlayer(ClubPlayerRepository playerRepo) {
        return playerRepo.getAllBy("r.player_id = ?", List.of(playerId), null, null)
                .getFirst();
    }
}

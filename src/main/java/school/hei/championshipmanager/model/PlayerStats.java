package school.hei.championshipmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private List<PlayerScore> scores;
}

package school.hei.championshipmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerScore {
    private String id;
    private String playerId;
    private String matchId;
    private Duration minuteOfGoal;
    private Boolean ownGoal;
}

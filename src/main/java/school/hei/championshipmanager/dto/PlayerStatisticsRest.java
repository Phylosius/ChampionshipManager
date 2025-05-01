package school.hei.championshipmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerStatisticsRest {
    private Integer scoredGoals;
    private PlayingTimeRest playingTime;
}

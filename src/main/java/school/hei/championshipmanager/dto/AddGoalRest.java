package school.hei.championshipmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddGoalRest {
    private String clubId;
    private String scorerIdentifier;
    private Integer minuteOfGoal;
}

package school.hei.championshipmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.hei.championshipmanager.enums.DurationUnit;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayingTimeRest {
    private Long value;
    private DurationUnit durationUnit;
}

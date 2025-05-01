package school.hei.championshipmanager.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClubScoreRest {
    private Integer score;
    private List<ScorerRest> scorers;
}

package school.hei.championshipmanager.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class MatchClub extends ClubRestMinimumInfo{
    private Integer score;
    private List<ScorerRest> scrorers;
}

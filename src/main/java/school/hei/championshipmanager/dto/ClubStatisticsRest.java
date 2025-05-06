package school.hei.championshipmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Comparator;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class ClubStatisticsRest extends ClubRest{
    private Integer rankingPoint;
    private Integer scoredGoals;
    private Integer concededGoals;
    private Integer differenceGoals;
    private Integer cleanSheetNumber;

    public static final Comparator<ClubStatisticsRest> RANKING_COMPARATOR = Comparator
            .comparing(ClubStatisticsRest::getRankingPoint, Comparator.nullsFirst(Comparator.reverseOrder()))
            .thenComparing(ClubStatisticsRest::getDifferenceGoals, Comparator.nullsFirst(Comparator.reverseOrder()))
            .thenComparing(ClubStatisticsRest::getCleanSheetNumber, Comparator.nullsFirst(Comparator.reverseOrder()));

    public static final Comparator<ClubStatisticsRest> NAME_ASC_COMPARATOR =
            Comparator.comparing(ClubStatisticsRest::getName);
}

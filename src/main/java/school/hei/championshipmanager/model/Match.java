package school.hei.championshipmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.hei.championshipmanager.enums.EventStatus;
import school.hei.championshipmanager.enums.MatchStatType;
import school.hei.championshipmanager.enums.PlayingClubSide;
import school.hei.championshipmanager.repository.*;

import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Match {
    private String id;
    private String seasonId;
    private String championshipId;
    private LocalDateTime date;
    private Club homeClub;
    private Club awayClub;
    private EventStatus status;

    public Season getSeason(SeasonRepo seasonRepo) {
        return seasonRepo.getById(seasonId);
    }

    public Championship getChampionship(ChampionshipRepo championshipRepo) {
        return championshipRepo.getById(championshipId);
    }

    public Integer getStat(PlayingClubSide clubSide, MatchStatType type,
                             ClubPlayerRepository clubPlayerRepository,
                             PlayerStatsRepo playerStatsRepo, PlayerScoreRepo playerScoreRepo,
                             SeasonRepo seasonRepo)
    {
        Club own;
        Club adv;

        if (clubSide == PlayingClubSide.HOME) {
            own = homeClub;
            adv = awayClub;
        }
        else if (clubSide == PlayingClubSide.AWAY) {
            own = awayClub;
            adv = homeClub;
        } else {
            throw new RuntimeException("Invalid club side");
        }

        Integer ownGoals = own.getScoredGoals(clubPlayerRepository, playerStatsRepo, playerScoreRepo,
                getSeason(seasonRepo).getYear(), id, true);

        Integer ownFakeGoals = own.getScoredGoals(clubPlayerRepository, playerStatsRepo, playerScoreRepo,
                getSeason(seasonRepo).getYear(), id, false) - ownGoals;

        Integer advGoals = adv.getScoredGoals(clubPlayerRepository, playerStatsRepo, playerScoreRepo,
                getSeason(seasonRepo).getYear(), id, true);

        Integer advFakeGoals = adv.getScoredGoals(clubPlayerRepository, playerStatsRepo, playerScoreRepo,
                getSeason(seasonRepo).getYear(), id, false) - advGoals;

        if (type == MatchStatType.RANKING_POINT){
            return getPoint((ownGoals + advFakeGoals), (advGoals + ownFakeGoals));
        } else if (type == MatchStatType.SCORED_GOAL) {
            return ownGoals;
        } else if (type == MatchStatType.DIFFERENCE_GOAL){
            return ownGoals - advGoals;
        } else if (type == MatchStatType.CONCEDED_GOAL) {

            return advGoals + ownFakeGoals;
        } else if (type == MatchStatType.CLEAN_SHEET_POINT) {
            return getCleanSheetPoint(advGoals);
        } else {
            throw new RuntimeException("Invalid match stat type");
        }

    }

    public static Integer getPoint(Integer ownGoals, Integer advGoals) {
        if (Objects.equals(ownGoals, advGoals)) {
            return 1;
        } else if (ownGoals < advGoals) {
            return 0;
        } else {
            return 3;
        }
    }

    public static Integer getCleanSheetPoint(Integer advGoals) {
        if (advGoals == 0) {
            return 1;
        }
        return 0;
    }


}

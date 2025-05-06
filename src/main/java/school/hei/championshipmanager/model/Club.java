package school.hei.championshipmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.hei.championshipmanager.enums.MatchStatType;
import school.hei.championshipmanager.enums.PlayingClubSide;
import school.hei.championshipmanager.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Club {
    private String id;
    private String name;
    private String acronym;
    private Integer creationYear;
    private String stadiumName;
    private String championshipId;
    private Coach coach;

    public List<PlayerScore> getPlayerScores(Integer seasonYear, String matchId, ClubPlayerRepository clubPlayerRepository, PlayerStatsRepo playerStatsRepo, PlayerScoreRepo playerScoreRepo) {
        List<PlayerScore> playerScores = new ArrayList<>();

        getPlayers(clubPlayerRepository).forEach(playerScore -> {
                   playerScore.getStats(playerStatsRepo, seasonYear, matchId)
                           .forEach(p_s -> playerScores.addAll(p_s.getScores(playerScoreRepo)));
        });

        return playerScores;
    }

    public List<ClubPlayer> getPlayers(ClubPlayerRepository clubPlayerRepository) {
        return clubPlayerRepository.getAllBy("r.club_id = ? AND r.is_active = true",
                List.of(id), null, null);
    }

    public Championship getChampionship(ChampionshipRepo championshipRepo) {
        return championshipRepo.getById(championshipId);
    }

    public Integer getScoredGoals(ClubPlayerRepository clubPlayerRepository, PlayerStatsRepo playerStatsRepo,
                                   PlayerScoreRepo playerScoreRepo, Integer seasonYear, String matchId, Boolean excludeOwned)
    {
        AtomicInteger scoredGoals = new AtomicInteger(0);
        List<ClubPlayer> players = getPlayers(clubPlayerRepository);
        players.forEach(p -> {
            scoredGoals.set(
                    scoredGoals.get() + p.getScoredGoals(playerStatsRepo, playerScoreRepo, seasonYear, matchId, excludeOwned)
            );
        });

        return scoredGoals.get();
    }

    public List<Match> getMatches(MatchRepository matchRepository, Integer seasonYear) {
        return matchRepository.getAllBySeasonYearAndClubId(seasonYear, id);
    }

    public Integer getStat(List<Match> matches, MatchStatType statType,
                           ClubPlayerRepository clubPlayerRepository,
                           PlayerStatsRepo playerStatsRepo, PlayerScoreRepo playerScoreRepo,
                           SeasonRepo seasonRepo)
    {
        AtomicInteger stat = new AtomicInteger(0);

        matches.forEach(m -> {
            PlayingClubSide side;

            if (m.getHomeClub().getId().equals(id)) {
                side = PlayingClubSide.HOME;
            } else if (m.getAwayClub().getId().equals(id)) {
                side = PlayingClubSide.AWAY;
            } else {
                side = null;
            }

            stat.set(
                    stat.get() + m.getStat(side, statType,
                            clubPlayerRepository,
                            playerStatsRepo, playerScoreRepo,
                            seasonRepo)
            );
        });

        return stat.get();
    }
}

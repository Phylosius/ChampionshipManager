package school.hei.championshipmanager.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import school.hei.championshipmanager.dto.ClubRest;
import school.hei.championshipmanager.dto.ClubStatisticsRest;
import school.hei.championshipmanager.enums.MatchStatType;
import school.hei.championshipmanager.model.Club;
import school.hei.championshipmanager.model.Match;
import school.hei.championshipmanager.repository.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@Component
public class ClubMapper implements ModelRepositoryMapper<Club> {

    private final CoachRepo coachRepo;
    private final CoachMapper coachMapper;
    private final ChampionshipRepo championshipRepo;
    private final MatchRepository matchRepository;
    private final ClubPlayerRepository clubPlayerRepository;
    private final PlayerScoreRepo playerScoreRepo;
    private final PlayerStatsRepo playerStatsRepo;
    private final SeasonRepo seasonRepo;

    @Override
    public List<?> toCreationParams(Club club) {
        return List.of(club.getId(), club.getName(),
                club.getCreationYear(), club.getAcronym(),
                club.getStadiumName(), club.getChampionshipId());
    }

    @Override
    public List<?> toUpdateParams(Club club) {
        return List.of(club.getName(), club.getCreationYear(),
                club.getAcronym(), club.getStadiumName(),
                club.getChampionshipId(), club.getId());
    }

    @Override
    public Club toModel(ResultSet rs) {
        try {
            Club club = new Club();

            club.setId(rs.getString("id"));
            club.setName(rs.getString("name"));
            club.setCreationYear(rs.getInt("creation_year"));
            club.setAcronym(rs.getString("acronym"));
            club.setStadiumName(rs.getString("stadium_name"));
            club.setChampionshipId(rs.getString("championship_id"));
            club.setCoach(
                    coachRepo.getById("")
            );

            return club;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ClubRest toDTO(Club club) {
        ClubRest dto = new ClubRest();

        dto.setId(club.getId());
        dto.setName(club.getName());
        dto.setAcronym(club.getAcronym());
        dto.setStadium(club.getStadiumName());
        dto.setYearCreation(club.getCreationYear());
        dto.setCoach(
                coachMapper.toDTO(club.getCoach())
        );

        return dto;
    }

    public Club toModel(ClubRest dto) {
        Club club = new Club();

        club.setId(dto.getId());
        club.setName(dto.getName());
        club.setAcronym(dto.getAcronym());
        club.setCreationYear(dto.getYearCreation());
        club.setChampionshipId(championshipRepo.getDefault().getId());

        return club;
    }

    public ClubStatisticsRest toStats (Club club, Integer seasonYear) {
        ClubStatisticsRest stats = new ClubStatisticsRest();

        stats.setId(club.getId());
        stats.setName(club.getName());
        stats.setAcronym(club.getAcronym());
        stats.setStadium(club.getStadiumName());
        stats.setYearCreation(club.getCreationYear());
        stats.setCoach(coachMapper.toDTO(club.getCoach()));

        List<Match> matches = club.getMatches(matchRepository, seasonYear);
        stats.setRankingPoint(
                club.getStat(matches, MatchStatType.RANKING_POINT,
                        clubPlayerRepository, playerStatsRepo, playerScoreRepo,
                        seasonRepo)
        );
        stats.setScoredGoals(
                club.getStat(matches, MatchStatType.SCORED_GOAL,
                        clubPlayerRepository, playerStatsRepo, playerScoreRepo,
                        seasonRepo)
        );
        stats.setDifferenceGoals(
                club.getStat(matches, MatchStatType.DIFFERENCE_GOAL,
                        clubPlayerRepository, playerStatsRepo, playerScoreRepo,
                        seasonRepo)
        );
        stats.setConcededGoals(
                club.getStat(matches, MatchStatType.CONCEDED_GOAL,
                        clubPlayerRepository, playerStatsRepo, playerScoreRepo,
                        seasonRepo)
        );
        stats.setCleanSheetNumber(
                club.getStat(matches, MatchStatType.CLEAN_SHEET_POINT,
                        clubPlayerRepository, playerStatsRepo, playerScoreRepo,
                        seasonRepo)
        );

        return stats;
    }
}

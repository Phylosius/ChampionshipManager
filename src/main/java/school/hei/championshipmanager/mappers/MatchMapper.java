package school.hei.championshipmanager.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import school.hei.championshipmanager.enums.EventStatus;
import school.hei.championshipmanager.model.Match;
import school.hei.championshipmanager.repository.ClubRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@Component
public class MatchMapper implements ModelRepositoryMapper<Match> {

    private final ClubRepo clubRepo;

    @Override
    public List<?> toCreationParams(Match match) {
        return List.of(match.getId(), match.getSeasonId(), match.getChampionshipId(),
                match.getHomeClub().getId(), match.getAwayClub().getId(),
                match.getDate(), match.getStatus());
    }

    @Override
    public List<?> toUpdateParams(Match match) {
        return List.of(match.getSeasonId(), match.getChampionshipId(),
                match.getHomeClub().getId(), match.getAwayClub().getId(),
                match.getDate(), match.getStatus(), match.getId());
    }

    @Override
    public Match toModel(ResultSet rs) {
        try {
            Match match = new Match();

            match.setId(rs.getString("id"));
            match.setSeasonId(rs.getString("season_id"));
            match.setChampionshipId(rs.getString("championship_id"));
            match.setStatus(EventStatus.valueOf(rs.getString("status")));
            match.setDate(rs.getTimestamp("date").toLocalDateTime());
            match.setHomeClub(
                    clubRepo.getById(rs.getString("home_club_id"))
            );
            match.setAwayClub(
                    clubRepo.getById(rs.getString("away_club_id"))
            );

            return match;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

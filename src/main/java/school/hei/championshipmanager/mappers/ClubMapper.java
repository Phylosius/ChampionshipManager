package school.hei.championshipmanager.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import school.hei.championshipmanager.model.Club;
import school.hei.championshipmanager.repository.ClubPlayerRepository;
import school.hei.championshipmanager.repository.CoachRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@Component
public class ClubMapper implements ModelRepositoryMapper<Club> {

    private final ClubPlayerRepository clubPlayerRepository;
    private final CoachRepo coachRepo;

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
            club.setPlayers(
                    clubPlayerRepository.getAllBy("r.club_id = ? AND is_active = true",
                            List.of(club.getId()), null, null)
            );
            club.setCoach(
                    coachRepo.getById("")
            );

            return club;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

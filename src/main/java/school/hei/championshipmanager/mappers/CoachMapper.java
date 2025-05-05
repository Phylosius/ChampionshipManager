package school.hei.championshipmanager.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import school.hei.championshipmanager.enums.EventStatus;
import school.hei.championshipmanager.model.Coach;
import school.hei.championshipmanager.repository.CountryRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@Component
public class CoachMapper implements ModelRepositoryMapper<Coach> {

    private final CountryRepo countryRepo;

    @Override
    public List<?> toCreationParams(Coach coach) {
        return List.of(coach.getId(), coach.getName(), coach.getCountry().getId(), coach.getClubId());
    }

    @Override
    public List<?> toUpdateParams(Coach coach) {
        return List.of(coach.getName(), coach.getCountry().getId(), coach.getClubId(), coach.getId());
    }

    @Override
    public Coach toModel(ResultSet rs) {
        try {
            Coach coach = new Coach();

            coach.setId(rs.getString("id"));
            coach.setName(rs.getString("name"));
            coach.setCountry(
                    countryRepo.getById(rs.getString("country_id"))
            );
            coach.setClubId(rs.getString("club_id"));

            return coach;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

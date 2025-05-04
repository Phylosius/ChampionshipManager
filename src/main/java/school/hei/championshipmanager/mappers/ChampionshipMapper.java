package school.hei.championshipmanager.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import school.hei.championshipmanager.model.Championship;
import school.hei.championshipmanager.repository.CountryRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@Component
public class ChampionshipMapper implements ModelRepositoryMapper<Championship> {

    private final CountryRepo countryRepo;

    @Override
    public List<?> toCreationParams(Championship champ) {
        return List.of(champ.getId(), champ.getName(), champ.getCountry().getId());
    }

    @Override
    public List<?> toUpdateParams(Championship champ) {
        return List.of(champ.getName(), champ.getCountry().getId(), champ.getId());
    }

    @Override
    public Championship toModel(ResultSet rs) {
        try {
            Championship champ = new Championship();

            champ.setId(rs.getString("id"));
            champ.setName(rs.getString("name"));
            champ.setCountry(
                    countryRepo.getById(rs.getString("country_id"))
            );

            return champ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

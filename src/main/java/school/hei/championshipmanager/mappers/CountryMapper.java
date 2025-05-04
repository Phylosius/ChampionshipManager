package school.hei.championshipmanager.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import school.hei.championshipmanager.enums.Continent;
import school.hei.championshipmanager.model.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@Component
public class CountryMapper implements ModelRepositoryMapper<Country> {

    @Override
    public List<?> toCreationParams(Country country) {
        return List.of(country.getId(), country.getName(), country.getContinent());
    }

    @Override
    public List<?> toUpdateParams(Country country) {
        return List.of(country.getName(), country.getContinent(), country.getId());
    }

    @Override
    public Country toModel(ResultSet rs) {
        try {
            Country country = new Country();

            country.setId(rs.getString("id"));
            country.setName(rs.getString("name"));
            country.setContinent(Continent.valueOf(rs.getString("continent")));

            return country;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

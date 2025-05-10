package school.hei.championshipmanager.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import school.hei.championshipmanager.dto.CreateSeasonRest;
import school.hei.championshipmanager.dto.SeasonRest;
import school.hei.championshipmanager.enums.EventStatus;
import school.hei.championshipmanager.model.Season;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@Component
public class SeasonMapper implements ModelRepositoryMapper<Season> {

    @Override
    public List<?> toCreationParams(Season season) {
        return List.of(season.getId(), season.getYear(), season.getStatus().toString());
    }

    @Override
    public List<?> toUpdateParams(Season season) {
        return List.of(season.getYear(), season.getStatus().toString(), season.getId());
    }

    @Override
    public Season toModel(ResultSet rs) {
        try {
            Season season = new Season();

            season.setId(rs.getString("id"));
            season.setYear(rs.getInt("year"));
            season.setStatus(EventStatus.valueOf(rs.getString("status")));

            //System.out.println(season);

            return season;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public SeasonRest toDTO(Season season) {
        SeasonRest dto = new SeasonRest();

        dto.setId(season.getId());
        dto.setYear(season.getYear());
        dto.setStatus(season.getStatus());
        dto.setAlias(season.getAlias());

        return dto;
    }

    public Season toModel(CreateSeasonRest dto) {
        Season season = new Season();

        season.setYear(dto.getYear());
        season.setStatus(EventStatus.NOT_STARTED);
        season.setId(season.getAlias());

        return season;
    }
}

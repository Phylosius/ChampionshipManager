package school.hei.championshipmanager.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import school.hei.championshipmanager.model.Player;
import school.hei.championshipmanager.repository.CountryRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@Component
public class PlayerMapper implements ModelRepositoryMapper<Player> {
    private final CountryRepo countryRepo;

    @Override
    public List<?> toCreationParams(Player player) {
        return List.of(player.getId(), player.getName(), player.getAge(), player.getCountry().getId());
    }

    @Override
    public List<?> toUpdateParams(Player player) {
        return List.of(player.getName(), player.getAge(), player.getCountry().getId(), player.getId());
    }

    @Override
    public Player toModel(ResultSet rs) {
        try {
            Player player = new Player();

            player.setId(rs.getString("id"));
            player.setName(rs.getString("name"));
            player.setAge(rs.getInt("age"));
            player.setCountry(
                    countryRepo.getById(rs.getString("country_id"))
            );

            return player;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

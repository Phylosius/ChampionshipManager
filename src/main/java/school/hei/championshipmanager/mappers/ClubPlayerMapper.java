package school.hei.championshipmanager.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import school.hei.championshipmanager.enums.PlayerPosition;
import school.hei.championshipmanager.model.ClubPlayer;
import school.hei.championshipmanager.repository.CountryRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@Component
public class ClubPlayerMapper implements ModelRepositoryMapper<ClubPlayer> {

    private final CountryRepo countryRepo;

    @Override
    public List<?> toCreationParams(ClubPlayer clubPlayer) {
        return List.of(clubPlayer.getRoleId(),
                clubPlayer.getClubId(), clubPlayer.getId(),
                clubPlayer.getNumber(), clubPlayer.getPosition(),
                clubPlayer.getActive());
    }

    @Override
    public List<?> toUpdateParams(ClubPlayer clubPlayer) {
        return List.of(
                clubPlayer.getClubId(), clubPlayer.getId(),
                clubPlayer.getNumber(), clubPlayer.getPosition(),
                clubPlayer.getActive(), clubPlayer.getId(), clubPlayer.getClubId());
    }

    @Override
    public ClubPlayer toModel(ResultSet rs) {
        try {
            ClubPlayer clubPlayer = new ClubPlayer();

            clubPlayer.setRoleId(rs.getString("role_id"));
            clubPlayer.setId(rs.getString("player_id"));
            clubPlayer.setClubId(rs.getString("club_id"));
            clubPlayer.setName(rs.getString("player_name"));
            clubPlayer.setAge(rs.getInt("player_age"));
            clubPlayer.setCountry(
                    countryRepo.getById(rs.getString("country_id"))
            );
            clubPlayer.setNumber(rs.getInt("player_number"));
            clubPlayer.setPosition(
                    PlayerPosition.valueOf(rs.getString("player_position"))
            );
            clubPlayer.setActive(rs.getBoolean("is_active"));

            return clubPlayer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

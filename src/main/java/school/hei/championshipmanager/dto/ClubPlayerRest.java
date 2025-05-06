package school.hei.championshipmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class ClubPlayerRest extends PlayerRest {
    private ClubRest club;

    public ClubPlayerRest(PlayerRest player, ClubRest club) {
        super(player);
        this.club = club;
    }
}

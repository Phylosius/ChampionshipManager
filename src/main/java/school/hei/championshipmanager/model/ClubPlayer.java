package school.hei.championshipmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import school.hei.championshipmanager.enums.PlayerPosition;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class ClubPlayer extends Player {
    private String clubId;
    private Integer number;
    private PlayerPosition position;
    private Boolean active;

    public Club getClub() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

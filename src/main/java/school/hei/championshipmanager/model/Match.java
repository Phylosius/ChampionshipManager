package school.hei.championshipmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.hei.championshipmanager.enums.EventStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Match {
    private String id;
    private String seasonId;
    private String championshipId;
    private LocalDateTime date;
    private Club homeClub;
    private Club awayClub;
    private EventStatus status;

    public Season getSeason() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Championship getChampionship() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

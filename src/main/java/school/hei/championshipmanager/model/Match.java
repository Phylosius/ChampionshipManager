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
    private Season season;
    private Championship championship;
    private LocalDateTime date;
    private Club homeClub;
    private Club awayClub;
    private EventStatus status;
}

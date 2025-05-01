package school.hei.championshipmanager.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.hei.championshipmanager.enums.EventStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MatchRest {
    private String id;
    private MatchClub clubPlayingHome;
    private MatchClub clubPlayingAway;
    private String stadium;
    private LocalDateTime matchDateTime;
    private EventStatus actualStatus;
}

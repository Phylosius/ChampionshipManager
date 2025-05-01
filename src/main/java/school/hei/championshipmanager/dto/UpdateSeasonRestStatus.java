package school.hei.championshipmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.hei.championshipmanager.enums.EventStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateSeasonRestStatus {
    private EventStatus status;
}

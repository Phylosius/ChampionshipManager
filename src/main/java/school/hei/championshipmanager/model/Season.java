package school.hei.championshipmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.hei.championshipmanager.enums.EventStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Season {
    private String id;
    private Integer year;
    private EventStatus status;

    public String getAlias() {
        return String.format("S%s-%s", year, year + 1);
    }

    public boolean setStatus(EventStatus status) {
        if (status.isAfter(this.status)) {
            this.status = status;
            return true;
        }
        return false;
    }
}

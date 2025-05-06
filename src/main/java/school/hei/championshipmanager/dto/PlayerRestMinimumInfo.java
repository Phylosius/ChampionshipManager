package school.hei.championshipmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerRestMinimumInfo {
    private String id;
    private String name;
    private Integer number;

    public PlayerRestMinimumInfo(PlayerRestMinimumInfo info) {
        this.id = info.getId();
        this.name = info.getName();
        this.number = info.getNumber();
    }
}

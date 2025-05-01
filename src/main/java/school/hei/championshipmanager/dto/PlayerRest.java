package school.hei.championshipmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import school.hei.championshipmanager.enums.PlayerPosition;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class PlayerRest extends PlayerRestMinimumInfo {
    private PlayerPosition position;
    private String nationality;
    private Integer age;
}

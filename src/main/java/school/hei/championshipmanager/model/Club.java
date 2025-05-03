package school.hei.championshipmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Club {
    private String id;
    private String name;
    private String acronym;
    private Integer creationYear;
    private String stadiumName;
    private List<ClubPlayer> players;
    private Coach coach;
}

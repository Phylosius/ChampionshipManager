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
    private String championshipId;
    private Coach coach;
    private List<ClubPlayer> players;

    public Championship getChampionship() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

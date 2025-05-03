package school.hei.championshipmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Championship {
    private String id;
    private String name;
    private Country country;

    public List<Club> getClubs() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

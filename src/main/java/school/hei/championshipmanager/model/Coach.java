package school.hei.championshipmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Coach {
    private String id;
    private String name;
    private Country country;

    public Club getClub() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

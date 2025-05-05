package school.hei.championshipmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.hei.championshipmanager.repository.ClubRepo;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Championship {
    private String id;
    private String name;
    private Country country;

    public List<Club> getClubs(ClubRepo clubRepo) {
        return clubRepo.getAllBy("championship_id = ?", List.of(id), null, null);
    }
}

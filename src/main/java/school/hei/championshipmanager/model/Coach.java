package school.hei.championshipmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.hei.championshipmanager.repository.ClubRepo;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Coach {
    private String id;
    private String name;
    private Country country;
    private String clubId;

    public Club getClub(ClubRepo clubRepo) {
        return clubRepo.getById(clubId);
    }
}

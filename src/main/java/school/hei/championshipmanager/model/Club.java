package school.hei.championshipmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.hei.championshipmanager.repository.ChampionshipRepo;
import school.hei.championshipmanager.repository.ClubPlayerRepository;
import school.hei.championshipmanager.repository.PlayerRepo;

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

    public List<ClubPlayer> getPlayers(ClubPlayerRepository clubPlayerRepository) {
        return clubPlayerRepository.getAllBy("r.club_id = ? AND is_active = true",
                List.of(id), null, null);
    }

    public Championship getChampionship(ChampionshipRepo championshipRepo) {
        return championshipRepo.getById(championshipId);
    }
}

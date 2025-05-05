package school.hei.championshipmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.hei.championshipmanager.enums.EventStatus;
import school.hei.championshipmanager.repository.ChampionshipRepo;
import school.hei.championshipmanager.repository.SeasonRepo;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Match {
    private String id;
    private String seasonId;
    private String championshipId;
    private LocalDateTime date;
    private Club homeClub;
    private Club awayClub;
    private EventStatus status;

    public Season getSeason(SeasonRepo seasonRepo) {
        return seasonRepo.getById(seasonId);
    }

    public Championship getChampionship(ChampionshipRepo championshipRepo) {
        return championshipRepo.getById(championshipId);
    }
}

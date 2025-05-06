package school.hei.championshipmanager.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.hei.championshipmanager.dto.ClubPlayerRest;
import school.hei.championshipmanager.dto.ClubRest;
import school.hei.championshipmanager.dto.ClubStatisticsRest;
import school.hei.championshipmanager.dto.PlayerRest;
import school.hei.championshipmanager.mappers.ClubMapper;
import school.hei.championshipmanager.mappers.ClubPlayerMapper;
import school.hei.championshipmanager.model.Club;
import school.hei.championshipmanager.model.ClubPlayer;
import school.hei.championshipmanager.model.Player;
import school.hei.championshipmanager.repository.ClubPlayerRepository;
import school.hei.championshipmanager.repository.ClubRepo;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ClubService {

    private final ClubRepo clubRepo;
    private final ClubMapper clubMapper;
    private final ClubPlayerRepository clubPlayerRepository;
    private final ClubPlayerMapper clubPlayerMapper;

    public List<ClubRest> getClubs(Integer page, Integer pageSize) {
        List<Club> clubs = clubRepo.getAll(page, pageSize);

        return clubs.stream().map(clubMapper::toDTO).toList();
    }

    public List<ClubRest> createOrUpdateClubs(List<ClubRest> clubs) {
        List<Club> models = clubs.stream().map(clubMapper::toModel).toList();

        models.forEach(clubRepo::save);

        return clubs;
    }

    public List<PlayerRest> getPlayers(String clubId, Integer page, Integer pageSize) {
        List<ClubPlayer> players = clubPlayerRepository.getAllByClubId(clubId, page, pageSize);

        return players.stream().map(clubPlayerMapper::toPlayerRest).toList();
    }

    public List<PlayerRest> updatePlayers(String clubId, List<PlayerRest> players) {
        clubPlayerRepository.deleteAllByClubId(clubId);

        List<ClubPlayer> toSave = players.stream().map(clubPlayerMapper::toModel).toList();
        toSave.forEach(p -> {
            p.setClubId(clubId);
            clubPlayerRepository.save(p);
        });

        return players;
    }

    public List<ClubStatisticsRest> getStatistics(Integer seasonYear, Boolean hasToBeClassified) {
        List<Club> clubs = clubRepo.getAll(null, null);
        List<ClubStatisticsRest> stats = clubs.stream().map(c -> clubMapper.toStats(c, seasonYear)).toList();

        if (hasToBeClassified) {
            return stats.stream().sorted(ClubStatisticsRest.RANKING_COMPARATOR).toList();
        } else {
            return stats.stream().sorted(ClubStatisticsRest.NAME_ASC_COMPARATOR).toList();
        }
    }

}

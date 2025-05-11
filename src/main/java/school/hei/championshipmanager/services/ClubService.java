package school.hei.championshipmanager.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.hei.championshipmanager.dto.*;
import school.hei.championshipmanager.exeptions.EntityNotFoundException;
import school.hei.championshipmanager.mappers.ClubMapper;
import school.hei.championshipmanager.mappers.ClubPlayerMapper;
import school.hei.championshipmanager.model.Club;
import school.hei.championshipmanager.model.ClubPlayer;
import school.hei.championshipmanager.repository.ClubPlayerRepository;
import school.hei.championshipmanager.repository.ClubRepo;
import school.hei.championshipmanager.repository.MatchRepository;
import school.hei.championshipmanager.repository.SeasonRepo;

import java.util.List;

@AllArgsConstructor
@Service
public class ClubService {

    private final ClubRepo clubRepo;
    private final ClubMapper clubMapper;
    private final ClubPlayerRepository clubPlayerRepository;
    private final ClubPlayerMapper clubPlayerMapper;
    private final MatchRepository matchRepository;
    private final SeasonRepo seasonRepo;

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
        Club club = clubRepo.getById(clubId);

        if (club == null) {
            throw new EntityNotFoundException(String.format("Club with id %s not found", clubId));
        }

        List<ClubPlayer> players = clubPlayerRepository.getAllByClubId(clubId, page, pageSize);

        return players.stream().map(clubPlayerMapper::toPlayerRest).toList();
    }

    public List<PlayerRest> updatePlayers(String clubId, List<PlayerRest> players) {
        clubPlayerRepository.deleteAllByClubId(clubId);

        if (clubRepo.getById(clubId) == null) {
            return null;
        }

        List<ClubPlayer> toSave = players.stream().map(clubPlayerMapper::toModel).toList();
        toSave.forEach(p -> {
            p.setClubId(clubId);
            clubPlayerRepository.save(p);
        });

        return players;
    }

    public List<ClubStatisticsRest> getStatistics(Integer seasonYear, Boolean hasToBeClassified) {
        List<Club> clubs = clubRepo.getAll(null, null);
        List<ClubStatisticsRest> stats = clubs.stream().map(c -> clubMapper.toStats(c, seasonYear, matchRepository)).toList();

        if (seasonRepo.getByYear(seasonYear) == null) {
            return null;
        }

        if (hasToBeClassified) {
            return stats.stream().sorted(ClubStatisticsRest.RANKING_COMPARATOR).toList();
        } else {
            return stats.stream().sorted(ClubStatisticsRest.NAME_ASC_COMPARATOR).toList();
        }
    }
}

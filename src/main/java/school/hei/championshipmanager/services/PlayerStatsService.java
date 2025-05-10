package school.hei.championshipmanager.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.hei.championshipmanager.dto.PlayerStatisticsRest;
import school.hei.championshipmanager.enums.DurationUnit;
import school.hei.championshipmanager.exeptions.EntityNotFoundException;
import school.hei.championshipmanager.mappers.PlayerStatsMapper;
import school.hei.championshipmanager.model.PlayerStats;
import school.hei.championshipmanager.repository.PlayerRepo;
import school.hei.championshipmanager.repository.PlayerStatsRepo;
import school.hei.championshipmanager.repository.SeasonRepo;

import java.util.List;

@AllArgsConstructor
@Service
public class PlayerStatsService {

    private final PlayerStatsMapper playerStatsMapper;
    private final PlayerStatsRepo playerStatsRepo;
    private final SeasonRepo seasonRepo;
    private final PlayerRepo playerRepo;

    public PlayerStatisticsRest getStatisticsOfPlayerById(String playerId, Integer seasonYear, DurationUnit durationUnit) {

        if (!playerRepo.exists(playerId)) {
            throw new EntityNotFoundException(String.format("Player of id %s not found", playerId));
        }

        if (seasonRepo.getByYear(seasonYear) == null) {
            throw new EntityNotFoundException(String.format("Season of year %s not found", seasonYear));
        }

        List<PlayerStats> stats = playerStatsRepo.getAll(playerId, seasonYear, null);

        return playerStatsMapper.toDTO(stats, durationUnit);
    }
}

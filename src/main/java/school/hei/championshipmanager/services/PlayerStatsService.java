package school.hei.championshipmanager.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.hei.championshipmanager.dto.PlayerStatisticsRest;
import school.hei.championshipmanager.enums.DurationUnit;
import school.hei.championshipmanager.mappers.PlayerStatsMapper;
import school.hei.championshipmanager.model.PlayerStats;
import school.hei.championshipmanager.repository.PlayerStatsRepo;
import school.hei.championshipmanager.repository.SeasonRepo;

@AllArgsConstructor
@Service
public class PlayerStatsService {

    private final PlayerStatsMapper playerStatsMapper;
    private final PlayerStatsRepo playerStatsRepo;
    private final SeasonRepo seasonRepo;

    public PlayerStatisticsRest getStatisticsOfPlayerById(String playerId, Integer seasonYear, DurationUnit durationUnit) {

        if (seasonRepo.getByYear(seasonYear) == null) {
            return null;
        }

        PlayerStats stats = playerStatsRepo.getAll(playerId, seasonYear, null).getFirst();

        return playerStatsMapper.toDTO(stats, durationUnit);
    }
}

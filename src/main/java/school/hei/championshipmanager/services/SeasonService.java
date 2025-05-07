package school.hei.championshipmanager.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.hei.championshipmanager.dto.CreateSeasonRest;
import school.hei.championshipmanager.dto.SeasonRest;
import school.hei.championshipmanager.enums.EventStatus;
import school.hei.championshipmanager.mappers.SeasonMapper;
import school.hei.championshipmanager.model.Season;
import school.hei.championshipmanager.repository.SeasonRepo;

import java.util.List;

@AllArgsConstructor
@Service
public class SeasonService {

    private final SeasonRepo seasonRepo;
    private final SeasonMapper seasonMapper;

    public List<SeasonRest> getSeasons(Integer page, Integer pageSize) {
        List<Season> seasons = seasonRepo.getAll(page, pageSize);

        return seasons.stream().map(seasonMapper::toDTO).toList();
    }

    public List<SeasonRest> createSeasons(List<CreateSeasonRest> seasons) {
        List<Season> models = seasons.stream().map(seasonMapper::toModel).toList();
        models.forEach(seasonRepo::add);

        return models.stream().map(seasonMapper::toDTO).toList();
    }


    public SeasonRest updateSeasonStatus(Integer seasonYear, EventStatus newStatus) {
        Season season = seasonRepo.getByYear(seasonYear);

        if (season.getStatus() != null && season.getStatus().isAfter(newStatus)) {
            return null;
        }

        seasonRepo.update(season);

        return seasonMapper.toDTO(season);
    }
}

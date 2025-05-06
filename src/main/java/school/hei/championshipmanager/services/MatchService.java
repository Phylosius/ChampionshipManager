package school.hei.championshipmanager.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.hei.championshipmanager.dto.MatchRest;
import school.hei.championshipmanager.enums.EventStatus;
import school.hei.championshipmanager.mappers.MatchMapper;
import school.hei.championshipmanager.model.Match;
import school.hei.championshipmanager.repository.MatchRepository;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;

    public List<MatchRest> getAllBySeason(
            Integer seasonYear,
            EventStatus matchStatus,
            String clubPlayingName,
            LocalDateTime matchAfter,
            LocalDateTime matchBeforeOrEquals
    )
    {
        List<Match> matches = matchRepository.getAll(seasonYear, matchStatus, clubPlayingName,
                matchAfter, matchBeforeOrEquals);

        return matches.stream().map(matchMapper::toDTO).toList();
    }
}

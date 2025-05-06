package school.hei.championshipmanager.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.hei.championshipmanager.dto.AddGoalRest;
import school.hei.championshipmanager.dto.MatchRest;
import school.hei.championshipmanager.dto.UpdateMatchRestStatus;
import school.hei.championshipmanager.enums.EventStatus;
import school.hei.championshipmanager.mappers.MatchMapper;
import school.hei.championshipmanager.mappers.PlayerScoreMapper;
import school.hei.championshipmanager.model.Match;
import school.hei.championshipmanager.model.PlayerScore;
import school.hei.championshipmanager.repository.MatchRepository;
import school.hei.championshipmanager.repository.PlayerScoreRepo;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;
    private final PlayerScoreMapper playerScoreMapper;
    private final PlayerScoreRepo playerScoreRepo;

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

    public MatchRest updateMatchStatus(
            String matchId,
            UpdateMatchRestStatus matchStatus
    )
    {
        Match match = matchRepository.getById(matchId);

        if (match.getStatus().isAfter(match.getStatus())) {
            return null;
        }

        match.setStatus(matchStatus.getStatus());

        matchRepository.update(match);

        return matchMapper.toDTO(match);
    }

    public MatchRest addGoal(String matchId, List<AddGoalRest> goals) {
        Match match = matchRepository.getById(matchId);

        if (match.getStatus() != EventStatus.STARTED) {
            return null;
        }

        List<PlayerScore> scores = goals.stream().map(
                g -> playerScoreMapper.toModel(matchId, g.getScorerIdentifier(), g)
        ).toList();

        scores.forEach(playerScoreRepo::add);

        matchRepository.getById(matchId);
        return matchMapper.toDTO(match);
    }
}

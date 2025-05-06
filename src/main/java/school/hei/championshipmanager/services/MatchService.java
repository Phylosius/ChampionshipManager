package school.hei.championshipmanager.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.hei.championshipmanager.dto.AddGoalRest;
import school.hei.championshipmanager.dto.MatchRest;
import school.hei.championshipmanager.dto.UpdateMatchRestStatus;
import school.hei.championshipmanager.enums.EventStatus;
import school.hei.championshipmanager.mappers.MatchMapper;
import school.hei.championshipmanager.mappers.PlayerScoreMapper;
import school.hei.championshipmanager.model.*;
import school.hei.championshipmanager.repository.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;
    private final PlayerScoreMapper playerScoreMapper;
    private final PlayerScoreRepo playerScoreRepo;
    private final ChampionshipRepo championshipRepo;
    private final ClubRepo clubRepo;
    private final SeasonRepo seasonRepo;

    public List<MatchRest> getAllBySeason(
            Integer seasonYear,
            EventStatus matchStatus,
            String clubPlayingName,
            LocalDateTime matchAfter,
            LocalDateTime matchBeforeOrEquals
    )
    {
        if (seasonRepo.getByYear(seasonYear) == null) {
            return null;
        }

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

    public List<MatchRest> make(Integer seasonYear) {
        Championship champ = championshipRepo.getDefault();
        Season season = seasonRepo.getByYear(seasonYear);

        List<Club> clubs = clubRepo.getAll(null, null);
        List<Match> madeMatches = new ArrayList<>();

        clubs.forEach(club -> {
            List<Club> others = new ArrayList<>(clubs).stream()
                    .filter(c -> !Objects.equals(c.getId(), club.getId())).toList();

            others.forEach(otherClub -> {
                Match match = new Match();

                match.setId(UUID.randomUUID().toString());
                match.setStatus(EventStatus.NOT_STARTED);
                match.setSeasonId(season.getId());
                match.setChampionshipId(champ.getId());
                match.setDate(LocalDateTime.now());

                match.setHomeClub(club);
                match.setAwayClub(otherClub);

                madeMatches.add(match);
            });
        });

        return madeMatches.stream().map(matchMapper::toDTO).toList();
    }
}

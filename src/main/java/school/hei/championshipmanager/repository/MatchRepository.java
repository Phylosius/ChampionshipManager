package school.hei.championshipmanager.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import school.hei.championshipmanager.enums.EventStatus;
import school.hei.championshipmanager.exeptions.EntityAlreadyExistException;
import school.hei.championshipmanager.exeptions.EntityNotFoundException;
import school.hei.championshipmanager.mappers.MatchMapper;
import school.hei.championshipmanager.model.Match;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class MatchRepository implements EntityRepo<Match, String> {

    private final MatchMapper mapper;
    private final BaseRepo<Match> baseRepo;

    @Override
    public List<Match> getAll(Integer page, Integer pageSize) {
        return getAllBy(null, null, page, pageSize);
    }

    @Override
    public Match getById(String id) throws EntityNotFoundException {
        return getAllBy("match.id = ?", List.of(id), null, null).getFirst();
    }

    public List<Match> getAllBySeasonYearAndClubId(Integer seasonYear, String clubId) {
        return getAllBy("(match.home_club_id = ? OR match.away_club_id = ?) AND season.year = ?", List.of(clubId, clubId, seasonYear), null, null);
    }

    public List<Match> getAll(Integer seasonYear, EventStatus matchStatus, String clubPlayingName,
                              LocalDateTime matchAfter, LocalDateTime matchBeforeOrEquals)
    {
        StringBuilder sql = new StringBuilder("season.year = ?");
        List<Object> params = new ArrayList<>(List.of(seasonYear));

        if (matchStatus != null) {
            sql.append(" AND match.status = ?::public.\"EVENT_STATUS\"");
            params.add(matchStatus.toString());
        }

        if (clubPlayingName != null) {
            sql.append(" AND (match.home_club_id ILIKE '%' || ? || '%' OR match.away_club_id ILIKE '%' || ? || '%')");
            params.addAll(List.of(clubPlayingName, clubPlayingName));
        }

        if (matchAfter != null) {
            sql.append(" AND match.date > ?");
            params.add(matchAfter);
        }

        if (matchBeforeOrEquals != null) {
            sql.append(" AND match.date <= ?");
            params.add(matchBeforeOrEquals);
        }

        return getAllBy(sql.toString(), params, null, null);
    }

    public List<Match> getAllBy(String conditionSql, List<?> sqlParams, Integer page, Integer pageSize) {
        return baseRepo.getAllBy("""
                SELECT
                match.id,
                match.season_id,
                match.championship_id,
                match.home_club_id,
                match.away_club_id,
                match.date,
                match.status
                
                FROM match
                 JOIN season ON season.id = match.season_id
                """, conditionSql, sqlParams, page, pageSize, mapper);

    }

    @Override
    public int add(Match match) throws EntityAlreadyExistException {

        if (exists(match)) {
            throw new EntityAlreadyExistException("Match already created");
        }

        return baseRepo.add("""
                INSERT INTO
                match
                (id, season_id, championship_id, home_club_id, away_club_id, date, status)
                VALUES
                (?, ?, ?, ?, ?, ?, ?::public."EVENT_STATUS")
                """, match, mapper);
    }

    @Override
    public int update(Match match) {
        return baseRepo.update("""
                UPDATE
                match
                SET
                season_id = ?, championship_id = ?, home_club_id = ?, away_club_id = ?,
                 date = ?, status = ?::public."EVENT_STATUS"
                WHERE id = ?
                """, match, mapper);
    }

    @Override
    public int delete(String id) throws EntityNotFoundException {
        return baseRepo.delete("DELETE FROM match WHERE id = ?", id);
    }

    public boolean exists(Match match) {
        return !getAllBy("match.season_id = ? AND match.away_club_id = ? AND match.home_club_id = ?",
                List.of(match.getSeasonId(), match.getAwayClub().getId(), match.getHomeClub().getId()),
                null, null).isEmpty();
    }
}

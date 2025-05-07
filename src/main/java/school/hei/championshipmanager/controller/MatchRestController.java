package school.hei.championshipmanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import school.hei.championshipmanager.dto.AddGoalRest;
import school.hei.championshipmanager.dto.MatchRest;
import school.hei.championshipmanager.dto.SeasonRest;
import school.hei.championshipmanager.dto.UpdateMatchRestStatus;
import school.hei.championshipmanager.enums.EventStatus;
import school.hei.championshipmanager.services.MatchService;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/matches")
public class MatchRestController {

    private final MatchService matchService;

    public MatchRestController(MatchService matchService) {
        this.matchService = matchService;
    }

    /**
     * Get all matches for a given season year.
     *
     * @param seasonYear the date of the season
     * @param matchStatus the status of the match (optional)
     * @param clubPlayingName the name of the club playing (optional)
     * @param matchAfter the date after which to get matches (optional, provided with matchBeforeOrEquals)
     * @param matchBeforeOrEquals the date before or equal to which to get matches (optional, provided with matchAfter)
     * @return a ResponseEntity containing the list of matches
     */
    @GetMapping("/{seasonYear}")
    public ResponseEntity<?> getAllBySeason(
        @PathVariable Integer seasonYear,
        @RequestParam(required = false) EventStatus matchStatus,
        @RequestParam(required = false) String clubPlayingName,
        @RequestParam(required = false) LocalDateTime matchAfter,
        @RequestParam(required = false) LocalDateTime matchBeforeOrEquals
    ) {
        try {
            List<MatchRest> retrieved = matchService.getAllBySeason(seasonYear, matchStatus, clubPlayingName, matchAfter, matchBeforeOrEquals);

            if (retrieved == null) {
                return ResponseEntity.status(404).body(String.format("Season with year %s not found", seasonYear));
            }

            return ResponseEntity.ok(retrieved);
        } catch (Exception e) {
//            return ResponseEntity.status(500).body(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Change a specific match status
     * 
     * Accepted update order: NOT_STARTED > STARTED > FINISHED
     * 
     * @param id the id of the match to update
     * @param statusUpdate the new status of the match
     * @return Match with updated status
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<?> changeStatus(
        @PathVariable String id,
        @RequestBody UpdateMatchRestStatus statusUpdate
    ) {
        try {
            MatchRest match = matchService.updateMatchStatus(id, statusUpdate);
            if (match == null) {
                return ResponseEntity.status(400).body("The status update must follow the following : NOT_STARTED > STARTED > FINISHED");
            }
            return ResponseEntity.ok(match);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    /***
     * Add goals by player to a specific match
     * 
     * Only match with status STARTED can be added goals
     * 
     * @param id the id of the match to update
     * @param addGoals the new goals to add
     * @return Match with updated goals
     */
    @PostMapping("/{id}/goals")
    public ResponseEntity<?> addGoals(
        @PathVariable String id,
        @RequestBody List<AddGoalRest> addGoals
    ) {
        try {
            MatchRest matchRest = matchService.addGoal(id, addGoals);
            if (matchRest == null) {
                return ResponseEntity.status(400).body("The match should be STARTED.");
            }
            return ResponseEntity.ok(matchRest);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}

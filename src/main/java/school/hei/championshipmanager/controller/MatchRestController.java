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
import school.hei.championshipmanager.dto.UpdateMatchRestStatus;
import school.hei.championshipmanager.enums.EventStatus;
import school.hei.championshipmanager.services.MatchService;

import java.time.LocalDateTime;


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
            return ResponseEntity.ok(matchService.getAllBySeason(seasonYear, matchStatus, clubPlayingName, matchAfter, matchBeforeOrEquals));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
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
        return ResponseEntity.status(501).body("Not implemented.");
    }

    /***
     * Add goals by player to a specific match
     * 
     * Only match with status STARTED can be added goals
     * 
     * @param id the id of the match to update
     * @param addGoal the new goal to add
     * @return Match with updated goals
     */
    @PostMapping("/{id}/goals")
    public ResponseEntity<?> addGoals(
        @PathVariable String id,
        @RequestBody AddGoalRest addGoal
    ) {
        return ResponseEntity.status(501).body("Not implemented.");
    }
}

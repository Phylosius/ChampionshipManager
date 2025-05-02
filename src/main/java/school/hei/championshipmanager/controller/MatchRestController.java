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


@RestController
@RequestMapping("/matches")
public class MatchRestController {

    /**
     * Get all matches for a given season year.
     *
     * @param seasonDate the date of the season
     * @param matchStatus the status of the match (optional)
     * @param clubPlayingName the name of the club playing (optional)
     * @param matchAfter the date after which to get matches (optional, provided with matchBeforeOrEquals)
     * @param matchBeforeOrEquals the date before or equal to which to get matches (optional, provided with matchAfter)
     * @return a ResponseEntity containing the list of matches
     */
    @GetMapping("/{seasonYear}")
    public ResponseEntity<?> getAllBySeason(
        @PathVariable Integer seasonDate,
        @RequestParam(required = false) EventStatus matchStatus,
        @RequestParam(required = false) String clubPlayingName,
        @RequestParam(required = false) Integer matchAfter,
        @RequestParam(required = false) Integer matchBeforeOrEquals
    ) {
        return ResponseEntity.status(501).body("Not implemented.");
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

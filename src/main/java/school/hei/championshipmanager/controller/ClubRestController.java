package school.hei.championshipmanager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import school.hei.championshipmanager.dto.ClubRest;
import school.hei.championshipmanager.dto.PlayerRest;

@RestController
@RequestMapping("/clubs")
public class ClubRestController {
    
    /***
     * Get clubs of the championship
     * 
     * @return List of the clubs
     */
    @GetMapping
    public ResponseEntity<?> getClubs() {
        return ResponseEntity.status(501).body("Not implemented.");
    }

    /***
     * Create new clubs or update if already exist
     * 
     * @param clubs List of clubs to create or update 
     * @return List of created or updated clubs
     */
    @PutMapping
    public ResponseEntity<?> createOrUpdateClubs(
        @RequestBody List<ClubRest> clubs
    ) {
        return ResponseEntity.status(501).body("Not implemented.");
    }

    /***
     * Get actual players of the specific club
     * 
     * @param id identifier of the club
     * @return List of actual players of the specific club
     */
    @GetMapping("/{id}/players")
    public ResponseEntity<?> getPlayers(
        @PathVariable String id
    ) {
        return ResponseEntity.status(501).body("Not implemented.");
    }

    /***
     * Change players of the specific club
     * 
     * Provided players inside the requestBody erase the existing players inside the club.
     * In case player is detached from club, it is still possible to retrieve his individual statistics 
     * and collective statistics do not change. 
     * For example, the player has 10 goals for the season, even if he is not part of the club anymore, 
     * the club statistics do not change (goals scored).
     * Finally, he must not be inside the list of players of the club and cannot make actions anymore for the remaining matches, if the season is not yet finished.        
     * In case, one of existing players is still attached to a club, API return 400 BAD_REQUEST.
     * 
     * @param id identifier of the specific club
     * @param players New players list of the club
     * @return List of actual players of the specific club
     */
    @PutMapping("/{id}/players")
    public ResponseEntity<?> changePlayers(
        @PathVariable String id,
        @RequestBody List<PlayerRest> players
    ) {
        return ResponseEntity.status(501).body("Not Implemented.");
    }

    /***
     * Get all clubs statistics for a specific season year
     * 
     * @param seasonYear the year of the season to get statistics for
     * @param hasToBeClassified if true the statistics will be ordered by rankings. if false, the statistics will be ordered by club name ASC 
     * @return All club statistics for the specific season
     */
    @GetMapping("/statistics/{seasonYear}")
    public ResponseEntity<?> getStatistics(
        @PathVariable Integer seasonYear,
        @RequestParam(required = false) Boolean hasToBeClassified
    ) {
        return ResponseEntity.status(501).body("Not implemented.");
    }
}

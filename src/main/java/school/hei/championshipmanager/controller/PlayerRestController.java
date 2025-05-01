package school.hei.championshipmanager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import school.hei.championshipmanager.dto.PlayerRest;

@RestController
@RequestMapping("/players")
public class PlayerRestController {
    
    /***
     * Get list of players in the championship
     * 
     * @param nameContaining Filter the return list by name containing ignore case
     * @param clubName Filter the return list by club name containing ignore case
     * @param ageMin Filter the return list by age greater than provided value
     * @param ageMax Filter the return list by age less than provided value
     * @return List of players with their clubs
     */
    @GetMapping
    public ResponseEntity<?> getPlayers(
            @RequestParam(name = "name", required = false) String nameContaining,
            @RequestParam(name = "clubName", required = false) String clubName,
            @RequestParam(name = "ageMinimum", required = false) Integer ageMin,
            @RequestParam(name = "ageMaximum", required = false) Integer ageMax
    ) {
        return ResponseEntity.status(501).body("Not implemented yet");
    }

    /***
     * Create or update players without attaching them into club
     * 
     * @param players List of players to cfeate or update
     * @return List of created or updated players
     */
    @PutMapping
    public ResponseEntity<?> createOrUpdatePlayers(
        @RequestBody List<PlayerRest> players
    ) {
        return ResponseEntity.status(501).body("Not implemented yet");
    }

}

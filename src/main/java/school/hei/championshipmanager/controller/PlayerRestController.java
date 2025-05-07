package school.hei.championshipmanager.controller;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import school.hei.championshipmanager.dto.PlayerRest;
import school.hei.championshipmanager.dto.PlayerStatisticsRest;
import school.hei.championshipmanager.enums.DurationUnit;
import school.hei.championshipmanager.services.ClubPlayerService;
import school.hei.championshipmanager.services.PlayerStatsService;

@AllArgsConstructor
@RestController
@RequestMapping("/players")
public class PlayerRestController {

    private final ClubPlayerService clubPlayerService;
    private final PlayerStatsService playerStatsService;

    /***
     * Get list of players in the championship
     * 
     * @param nameContaining Filter the return list by name containing ignore case
     * @param clubNameContaining Filter the return list by club name containing ignore case
     * @param ageMin Filter the return list by age greater than provided value
     * @param ageMax Filter the return list by age less than provided value
     * @return List of players with their clubs
     */
    @GetMapping
    public ResponseEntity<?> getPlayers(
            @RequestParam(name = "name", required = false) String nameContaining,
            @RequestParam(name = "clubNameContaining", required = false) String clubNameContaining,
            @RequestParam(name = "ageMinimum", required = false) Integer ageMin,
            @RequestParam(name = "ageMaximum", required = false) Integer ageMax,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize
    ) {
        try {
            return ResponseEntity.ok(
                    clubPlayerService.getPlayers(nameContaining, clubNameContaining, ageMin, ageMax, page, pageSize)
            );
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    /***
     * Create or update players without attaching them into club
     * 
     * @param players List of players to create or update
     * @return List of created or updated players
     */
    @PutMapping
    public ResponseEntity<?> createOrUpdatePlayers(
        @RequestBody List<PlayerRest> players
    ) {
        try {
            return ResponseEntity.status(201).body(clubPlayerService.createOrUpdatePlayers(players));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Don't work");
        }
    }

    /**
     * Get statistics for a specific player
     * 
     * Important ! Note that own goals are not considered as goal inside goals scored.
     * @param id The id of the player to get statistics for
     * @param seasonYear The date of the season to get statistics for
     * @return Player statistics
     */
    @GetMapping("/{id}/statistics/{seasonYear}")
    public ResponseEntity<?> getStatisticsOfPlayerById(
            @PathVariable String id,
            @PathVariable Integer seasonYear,
            @RequestParam(required = false, defaultValue = "SECOND") DurationUnit durationUnit
            )
    {
        try {
            PlayerStatisticsRest retrieved = playerStatsService.getStatisticsOfPlayerById(id, seasonYear, durationUnit);

            if (retrieved == null) {
                return ResponseEntity.status(404).body(String.format("Season with year %s not found", seasonYear));
            }

            return ResponseEntity.ok(retrieved);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Don't work");
        }
    }

}

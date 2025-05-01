package school.hei.championshipmanager.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/matchMaker")
public class MatchMakerController {

    /**
     * Create all matches for a specific season including all clubs
     * 
     * @param seasonYear the season to generate match for (must be a STARTED (not FINISHED) season)
     * @return List of created matches for the season
     */
    @PostMapping("/{seasonYear}")
    public ResponseEntity<?> createAllMatches(
        @PathVariable LocalDate seasonYear
    ) {
        return ResponseEntity.status(501).body("Not implemented.");
    }
}

package school.hei.championshipmanager.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.hei.championshipmanager.services.MatchService;

@AllArgsConstructor
@RestController
@RequestMapping("/matchMaker")
public class MatchMakerController {

    private final MatchService matchService;

    /**
     * Create all matches for a specific season including all clubs
     * 
     * @param seasonYear the season to generate match for (must be a STARTED (not FINISHED) season)
     * @return List of created matches for the season
     */
    @PostMapping("/{seasonYear}")
    public ResponseEntity<?> createAllMatches(
        @PathVariable Integer seasonYear
    ) {
        try {
            return ResponseEntity.ok(matchService.make(seasonYear));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}

package school.hei.championshipmanager.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import school.hei.championshipmanager.dto.CreateSeasonRest;

@RestController
@RequestMapping("/seasons")
public class SeasonRestController {

    /**
     * Get list of existing seasons
     * 
     * @return List of seasons
     */
    @GetMapping
    public ResponseEntity<?> getSeasons() {
        return ResponseEntity.status(501).body("Not implemented.");
    }

    /**
     * Create list of new seasons
     * 
     * Required request body without ID or status, default computed status is NOT_STARTED.
     * @param seasons List of seasons to create
     * @return List of all seasons including new seasons created
     */
    @PostMapping
    public ResponseEntity<?> createSeasons(
        @RequestBody List<CreateSeasonRest> seasons
    ) {
        return ResponseEntity.status(501).body("Not implemented.");
    }

    /**
     * Update a specific season status
     * 
     * Accepted update order : NOT_STARTED > STARTED > FINISHED.
     * @param seasonYear Date of the season to update status
     * @return Season with updated status
     */
    @PutMapping("/{seasonYear}/status")
    public ResponseEntity<?> updateSeasonStatus(
        @PathVariable Integer seasonYear
    ) {
        return ResponseEntity.status(501).body("Not implemented.");
    }
}

package school.hei.championshipmanager.controller;

import java.util.List;
import java.util.NoSuchElementException;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import school.hei.championshipmanager.dto.CreateSeasonRest;
import school.hei.championshipmanager.dto.SeasonRest;
import school.hei.championshipmanager.dto.UpdateSeasonRestStatus;
import school.hei.championshipmanager.exeptions.EntityAlreadyExistException;
import school.hei.championshipmanager.services.SeasonService;

@AllArgsConstructor
@RestController
@RequestMapping("/seasons")
public class SeasonRestController {

    private final SeasonService seasonService;

    /**
     * Get list of existing seasons
     * 
     * @return List of seasons
     */
    @GetMapping
    public ResponseEntity<?> getSeasons(
         @RequestParam(required = false) Integer page,
         @RequestParam(required = false) Integer pageSize
    )
    {
        try {
            return ResponseEntity.ok(seasonService.getSeasons(page, pageSize));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
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
    )
    {
        try {
            return ResponseEntity.ok(seasonService.createSeasons(seasons));
        } catch (EntityAlreadyExistException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    /**
     * Update a specific season status
     * 
     * Accepted update order : NOT_STARTED > STARTED > FINISHED.
     * @param seasonYear Year of the season to update status
     * @return Season with updated status
     */
    @PutMapping("/{seasonYear}/status")
    public ResponseEntity<?> updateSeasonStatus(
            @PathVariable Integer seasonYear,
            @RequestParam UpdateSeasonRestStatus updateStatus
            ) {
        try {
            SeasonRest retrieved = seasonService.updateSeasonStatus(seasonYear, updateStatus.getStatus());

            if (retrieved == null) {
                return ResponseEntity.status(404).body("Season not found");
            }

            return ResponseEntity.ok(retrieved);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}

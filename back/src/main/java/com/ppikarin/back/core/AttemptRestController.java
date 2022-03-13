package com.ppikarin.back.core;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/attempts")
@Validated
@CrossOrigin
public class AttemptRestController {

    private final ChallengeService challengeService;

    @PostMapping
    ResponseEntity<Attempt> postResult(@RequestBody @Valid AttemptRDTO attemptDTO) {
        return ResponseEntity.ok(challengeService.verifyAttempt(attemptDTO));
    }

    @GetMapping
    ResponseEntity<List<AttemptDTO>> getStatistics(@RequestParam("humanName") String humanName) {
        return ResponseEntity.ok(challengeService.getStatsForHuman(humanName));
    }

    @GetMapping("/all")
    ResponseEntity<List<AttemptDTO>> getStatistics() {
        return ResponseEntity.ok(challengeService.getAllStats());
    }
}

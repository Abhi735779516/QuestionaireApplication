package com.examify.leaderboard.controller;

import com.examify.leaderboard.service.LeaderboardService;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/leaderboard")
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    @GetMapping("/{examName}")
    public Set<ZSetOperations.TypedTuple<String>> getLeaderboard(
            @PathVariable String examName
    ) {
        return leaderboardService.getTopScores(examName);
    }
}

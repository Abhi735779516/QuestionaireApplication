package com.examify.exam.controller;

import com.examify.exam.service.LeaderboardService;
import jakarta.servlet.http.HttpServletRequest;
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

    @GetMapping("/{examId}/top")
    public Set<ZSetOperations.TypedTuple<String>> topUsers(
            @PathVariable Long examId,
            @RequestParam(defaultValue = "10") int limit
    ) {
        return leaderboardService.topN(examId, limit);
    }

    @GetMapping("/{examId}/my-rank")
    public Long myRank(
            @PathVariable Long examId,
            HttpServletRequest request
    ) {
        String userEmail = request.getHeader("X-User-Email");
        return leaderboardService.getUserRank(examId, userEmail);
    }
}

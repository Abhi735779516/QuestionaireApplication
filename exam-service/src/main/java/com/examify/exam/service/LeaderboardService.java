package com.examify.exam.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LeaderboardService {

    private final RedisTemplate<String, String> redisTemplate;

    public LeaderboardService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private String leaderboardKey(Long examId) {
        return "leaderboard:exam:" + examId;
    }

    // Add or update score
    public void addScore(Long examId, String userEmail, int score) {
        redisTemplate.opsForZSet()
                .add(leaderboardKey(examId), userEmail, score);
    }

    // Top N users
    public Set<ZSetOperations.TypedTuple<String>> topN(Long examId, int limit) {
        return redisTemplate.opsForZSet()
                .reverseRangeWithScores(leaderboardKey(examId), 0, limit - 1);
    }

    // User rank (1-based)
    public Long getUserRank(Long examId, String userEmail) {
        Long rank = redisTemplate.opsForZSet()
                .reverseRank(leaderboardKey(examId), userEmail);
        return rank == null ? null : rank + 1;
    }
}

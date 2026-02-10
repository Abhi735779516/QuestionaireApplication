package com.examify.leaderboard.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LeaderboardService {

    private final StringRedisTemplate redisTemplate;

    public LeaderboardService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void updateScore(String examName, String userEmail, int score) {
        redisTemplate.opsForZSet()
                .add("LEADERBOARD:" + examName, userEmail, score);
    }

    public Set<ZSetOperations.TypedTuple<String>> getTopScores(String examName) {
        return redisTemplate.opsForZSet()
                .reverseRangeWithScores("LEADERBOARD:" + examName, 0, 9);
    }
}

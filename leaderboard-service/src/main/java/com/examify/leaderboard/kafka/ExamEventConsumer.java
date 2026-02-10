package com.examify.leaderboard.kafka;

import com.examify.leaderboard.event.ExamSubmittedEvent;
import com.examify.leaderboard.service.LeaderboardService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ExamEventConsumer {

    private final LeaderboardService leaderboardService;

    public ExamEventConsumer(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    @KafkaListener(
            topics = "exam-submitted",
            groupId = "leaderboard-group"
    )
    public void consume(ExamSubmittedEvent event) {

        leaderboardService.updateScore(
                event.getExamName(),
                event.getUserEmail(),
                event.getScore()
        );
    }
}

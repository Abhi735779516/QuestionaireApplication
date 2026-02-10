package com.examify.exam.service;

import com.examify.exam.entity.Exam;
import com.examify.exam.entity.ExamAttempt;
import com.examify.exam.repository.ExamAttemptRepository;
import com.examify.exam.repository.ExamRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExamService {

    private final LeaderboardService leaderboardService;
    private final ExamRepository examRepository;
    private final ExamAttemptRepository attemptRepository;

    public ExamService(LeaderboardService leaderboardService, ExamRepository examRepository,
                       ExamAttemptRepository attemptRepository) {
        this.leaderboardService = leaderboardService;
        this.examRepository = examRepository;
        this.attemptRepository = attemptRepository;
    }

    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    public ExamAttempt submitExam(String userEmail, Long examId, int score) {

        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        ExamAttempt attempt = new ExamAttempt();
        attempt.setUserEmail(userEmail);
        attempt.setScore(score);
        attempt.setExam(exam);
        attempt.setAttemptedAt(LocalDateTime.now());

        ExamAttempt saved = attemptRepository.save(attempt);

        // 🔥 ADD TO LEADERBOARD
        leaderboardService.addScore(examId, userEmail, score);

        return saved;
    }


    public List<ExamAttempt> myAttempts(String userEmail) {
        return attemptRepository.findByUserEmail(userEmail);
    }
}

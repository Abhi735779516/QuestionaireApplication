package com.examify.exam.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "exam_attempts")
public class ExamAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;

    private int score;

    private LocalDateTime attemptedAt;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    // getters & setters
    public Long getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public int getScore() {
        return score;
    }

    public LocalDateTime getAttemptedAt() {
        return attemptedAt;
    }

    public Exam getExam() {
        return exam;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setAttemptedAt(LocalDateTime attemptedAt) {
        this.attemptedAt = attemptedAt;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}

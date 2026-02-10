package com.examify.leaderboard.event;

import java.io.Serializable;

public class ExamSubmittedEvent implements Serializable {

    private String userEmail;
    private String examName;
    private int score;

    public ExamSubmittedEvent() {
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getExamName() {
        return examName;
    }

    public int getScore() {
        return score;
    }
}

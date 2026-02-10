package com.examify.exam.event;

import java.io.Serializable;

public class ExamSubmittedEvent implements Serializable {

    private String userEmail;
    private String examName;
    private int score;

    public ExamSubmittedEvent() {}

    public ExamSubmittedEvent(String userEmail, String examName, int score) {
        this.userEmail = userEmail;
        this.examName = examName;
        this.score = score;
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

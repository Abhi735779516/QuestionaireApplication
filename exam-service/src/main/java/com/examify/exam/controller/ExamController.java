package com.examify.exam.controller;

import com.examify.exam.entity.Exam;
import com.examify.exam.entity.ExamAttempt;
import com.examify.exam.event.ExamSubmittedEvent;
import com.examify.exam.service.ExamService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping("/create")
    public Exam createExam(@RequestBody Exam exam) {
        return examService.createExam(exam);
    }

    @PostMapping("/submit")
    public String submitExam(
            @RequestHeader("X-User-Email") String email,
            @RequestParam String examName,
            @RequestParam int score
    ) {
        ExamSubmittedEvent event =
                new ExamSubmittedEvent(email, examName, score);

        examEventProducer.sendExamSubmittedEvent(event);

        return "Exam submitted successfully";
    }


    @GetMapping("/my-attempts")
    public List<ExamAttempt> myAttempts(HttpServletRequest request) {
        String userEmail = request.getHeader("X-User-Email");
        return examService.myAttempts(userEmail);
    }
}

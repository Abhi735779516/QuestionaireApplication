package com.examify.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/exam-fallback")
    public String examFallback() {
        return "Exam Service is currently unavailable. Please try again later.";
    }

    @GetMapping("/auth-fallback")
    public String authFallback() {
        return "Auth Service is currently unavailable. Please try again later.";
    }
}

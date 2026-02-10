package com.examify.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProtectedController {

    @GetMapping("/protected")
    public String protectedApi(Authentication authentication) {
        return "Hello " + authentication.getName() + ", JWT is valid 🎉";
    }
}

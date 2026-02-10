package com.examify.exam.security;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class XUserEmailFilter extends OncePerRequestFilter {

    private static final String USER_HEADER = "X-User-Email";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String userEmail = request.getHeader(USER_HEADER);

        // Allow health endpoint without header
        if (request.getRequestURI().contains("/exam/health")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (userEmail == null || userEmail.isBlank()) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Missing X-User-Email header");
            return;
        }

        filterChain.doFilter(request, response);
    }
}

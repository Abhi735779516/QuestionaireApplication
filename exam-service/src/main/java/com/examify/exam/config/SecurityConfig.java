package com.examify.exam.config;

import com.examify.exam.security.XUserEmailFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final XUserEmailFilter xUserEmailFilter;

    public SecurityConfig(XUserEmailFilter xUserEmailFilter) {
        this.xUserEmailFilter = xUserEmailFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/exam/health").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        xUserEmailFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}

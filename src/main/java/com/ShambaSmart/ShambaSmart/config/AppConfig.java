package com.ShambaSmart.ShambaSmart.config;

import com.ShambaSmart.ShambaSmart.jwt.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public JwtUtils jwtUtil() {
        return new JwtUtils();
    }
}

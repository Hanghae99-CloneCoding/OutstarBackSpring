package com.sparta.hh99_clonecoding.config;

import com.sparta.hh99_clonecoding.jwt.JwtFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                .allowedOrigins("*")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("*")
                .exposedHeaders(JwtFilter.AUTHORIZATION_HEADER);
    }
}
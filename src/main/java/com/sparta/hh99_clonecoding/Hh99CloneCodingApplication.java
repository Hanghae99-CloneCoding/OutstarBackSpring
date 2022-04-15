package com.sparta.hh99_clonecoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Hh99CloneCodingApplication {
    public static void main(String[] args) {
        SpringApplication.run(Hh99CloneCodingApplication.class, args);
    }
}

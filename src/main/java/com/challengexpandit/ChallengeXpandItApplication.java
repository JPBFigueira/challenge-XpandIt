package com.challengexpandit;

import com.challengexpandit.repository.MoviesRepository;
import com.challengexpandit.service.MoviesService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChallengeXpandItApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChallengeXpandItApplication.class, args);
    }

    @Bean
    public MoviesService moviesService(MoviesRepository moviesRepository) {
        return new MoviesService(moviesRepository);
    }

}

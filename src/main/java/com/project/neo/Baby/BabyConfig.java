package com.project.neo.Baby;

import com.project.neo.BabyRepository.Babyrepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
//creates two baby objects in the mongodb database at runtime

@Configuration
public class BabyConfig {

    @Bean //ensures that this runs
    CommandLineRunner commandLineRunner(Babyrepository repository) {
        return args -> {
            Baby baby1 = new Baby(124790);
            Baby baby2 = new Baby(247619);
            repository.saveAll(List.of(baby1, baby2));
        };
    }
}

package com.project.neo.Baby;

import com.project.neo.AmazonS3.S3Service;
import com.project.neo.BabyRepository.Babyrepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

/**
 * This class automatically creates 3 baby objects when the server is launched.
 */
@Configuration
public class BabyConfig {

    @Bean
    CommandLineRunner commandLineRunner(Babyrepository repository) {
        return args -> {
            BabyService service = new BabyService(repository);

            DateTimeFormatter df2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date1 = LocalDate.parse("27/12/2021", df2);
            LocalDate date2 = LocalDate.parse("29/12/2021", df2);

            Integer int1 = 124790;
            Integer int2 = 247619;
            Integer int3 = 248575;

            Baby baby1 = new Baby(int1);
            Baby baby2 = new Baby(int2);
            Baby baby3 = new Baby(int3);

            baby1.getCali_grad().put(date1, 40.0);
            baby1.getCali_intercept().put(date1, 23.0);
            baby1.getDelay().put(date1, (long) 20.0);

            baby1.getCali_grad().put(date2, 3.0);
            baby1.getCali_intercept().put(date2, 4.0);
            baby1.getDelay().put(date2, (long) 40.0);

            repository.save(baby1);
            repository.save(baby2);
            repository.save(baby3);



    };

}}


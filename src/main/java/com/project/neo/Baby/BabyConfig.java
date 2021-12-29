package com.project.neo.Baby;

import com.project.neo.BabyRepository.Babyrepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
//creates two baby objects in the mongodb database at runtime. Will have to delete this when submitting otherwise will keep restoring the database.

@Configuration
public class BabyConfig {

    @Bean //ensures that this runs during runtime
    CommandLineRunner commandLineRunner(Babyrepository repository) {
        return args -> {
            BabyService service = new BabyService(repository);
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            LocalDateTime period = LocalDateTime.parse("27/12/2021 10:20:19", df);





            Integer int1 = 124790;
            Integer int2 = 247619;
            Integer int3 = 248575;

            Baby baby1 = new Baby(int1);
            Baby baby2 = new Baby(int2);
            Baby baby3 = new Baby(int3);

            repository.saveAll(List.of(baby1, baby2, baby3));

            String date = "27/12/2021 10:20:19";
            String[] d = date.split(" ");
            System.out.println(d[0]);
            System.out.println(d[1]);
            System.out.println(period.minusMinutes(20));


    };

}}


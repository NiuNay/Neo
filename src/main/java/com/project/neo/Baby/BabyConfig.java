package com.project.neo.Baby;

import com.project.neo.BabyRepository.Babyrepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
//creates two baby objects in the mongodb database at runtime

@Configuration
public class BabyConfig {

    @Bean //ensures that this runs during runtime
    CommandLineRunner commandLineRunner(Babyrepository repository) {
        return args -> {
            BabyService service = new BabyService(repository);

            Integer int1 = 124790;
            Integer int2 = 247619;
            Integer int3 = 248575;

            Baby baby1 = new Baby(int1);
            Baby baby2 = new Baby(int2);
            Baby baby3 = new Baby(int3);


            //HashMap<String, String> note = new HashMap<>();

            //note.put("2:03PM", "hello there.");

            //baby3.setNoteTimestamp(note);

            repository.saveAll(List.of(baby1, baby2, baby3));
            //System.out.print("Bean 1 Run");

            //service.addNewBaby(baby3);

            //Integer id = 124790;

            //service.add_NoteTimeStamp("2:03PM", "HI", id);
            //service.add_PrickTimeStamp("2:03PM", 2.5, id);
            //service.add_SweatTimeStamp("2:03PM", "HI", id);
            //service.add_SweatTimeStamp("5:03PM", "TEST2", id);
        };
    }

}

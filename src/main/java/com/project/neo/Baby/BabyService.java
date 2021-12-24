package com.project.neo.Baby;

import com.project.neo.BabyRepository.Babyrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

//This class contains the business functions
@Service
public class BabyService {
    private final Babyrepository babyrepository;

    @Autowired
    public BabyService(Babyrepository babyrepository) {
        this.babyrepository = babyrepository;
    }

    public List<Baby> returnBaby() {
        return babyrepository.findAll(); //this is where service layer interacts with data layer
    }

    public void addNewBaby(Baby baby) {
        System.out.println(baby);
        babyrepository.save(baby);
        System.out.println("baby saved");
    }

    public void deleteBaby(int id) {
        boolean exists = babyrepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Baby with Id: " + id + " does not exist.");
        }
        babyrepository.deleteById(id);
        System.out.println("Baby Id: " + id + " has been deleted.");
    }

    public void add_NoteTimeStamp(Instant time_instant, String note, int id) {
        boolean exists = babyrepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Baby with ID: " + id + "does not exist.");
        }

        ((babyrepository.findBabyByID(id)).getNoteTimestamp()).put(time_instant, note); // returns hashmap of returned baby object

    }

    public void add_PrickTimeStamp(Instant time_instant, BigDecimal prick_data, int id) {
        boolean exists = babyrepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Baby with ID: " + id + "does not exist.");
        }

        ((babyrepository.findBabyByID(id)).getPrickTimestamp()).put(time_instant, prick_data); // returns hashmap of returned baby object

    }

    public void add_SweatTimeStamp(Instant time_instant, BigDecimal sweat_data, int id) {
        boolean exists = babyrepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Baby with ID: " + id + "does not exist.");
        }

        ((babyrepository.findBabyByID(id)).getSweatTimestamp()).put(time_instant, sweat_data); // returns hashmap of returned baby object

    }

}

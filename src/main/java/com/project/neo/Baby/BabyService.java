package com.project.neo.Baby;

import com.project.neo.BabyRepository.Babyrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//This class contains the business functions
@Service
public class BabyService {
    private final Babyrepository babyrepository;

    @Autowired
    public BabyService(Babyrepository babyrepository) {
        this.babyrepository = babyrepository;
    }

    // method does not work, check over
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

    public void add_NoteTimeStamp(String time_instant, String note, Integer id) {
        boolean exists = babyrepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Baby with ID: " + id + "does not exist.");
        }

        Optional<Baby> opt = babyrepository.getBabyById(id);

        if(opt.isPresent()) {
            opt.get().getNoteTimestamp().put(time_instant, note);
            babyrepository.save(opt.get());
        }

        else {
            System.out.println("NOT FOUND");
        }

    }

    public void add_PrickTimeStamp(String time_instant, double prick_data, int id) {
        boolean exists = babyrepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Baby with ID: " + id + "does not exist.");
        }

        Optional<Baby> opt = babyrepository.getBabyById(id);

        if(opt.isPresent()) {
            opt.get().getPrickTimestamp().put(time_instant, prick_data);
            babyrepository.save(opt.get());
        }

        else {
            System.out.println("NOT FOUND");
        }
    }

    public void add_SweatTimeStamp(String time_instant, String sweat_data, int id) {
        boolean exists = babyrepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Baby with ID: " + id + "does not exist.");
        }

        Optional<Baby> opt = babyrepository.getBabyById(id);

        if(opt.isPresent()) {
            opt.get().getSweatTimestamp().put(time_instant, sweat_data);
            babyrepository.save(opt.get());
        }

        else {
            System.out.println("NOT FOUND");
        }
    }

}

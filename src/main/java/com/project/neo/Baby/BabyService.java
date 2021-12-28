package com.project.neo.Baby;

import com.project.neo.BabyRepository.Babyrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
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

    //must calibrate before you
    public void add_SweatTimeStamp(String time_instant, Double sweat_data, int id) {
        boolean exists = babyrepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Baby with ID: " + id + "does not exist.");
        }

        Optional<Baby> opt = babyrepository.getBabyById(id);

        if(opt.isPresent()) {
            opt.get().getSweatTimestamp().put(time_instant, (sweat_data-opt.get().getCali_intercept())/opt.get().getCali_grad());
            babyrepository.save(opt.get());
        }

        else {
            System.out.println("NOT FOUND");
        }
    }

    public Optional<Baby> returnSingleBaby(int id) {
        boolean exists = babyrepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Baby with ID: " + id + "does not exist.");
        }

        UpdateSweatLevels(id); //This will update sweat values found in the csv
        Optional<Baby> opt = babyrepository.getBabyById(id);

        return opt;
    }

    //takes the id of the selected baby and retrieves sweat info from the database
    public void UpdateSweatLevels (int i) {
        String file = "C:\\Users\\65978\\OneDrive - Imperial College London\\Desktop\\"+ i + ".csv"; // -< This is the path where the csv is saved.
        BufferedReader reader1 = null;
        String line = "";

        System.out.println(file);
        try {
            reader1 = new BufferedReader(new FileReader(file));
            System.out.println("In here");
            int j = 1;
            while ((line = reader1.readLine()) != null) {

                if (j != 1) {

                    String[] row = line.split(","); //element 1 is first column, element 2 is second column
                    add_SweatTimeStamp(row[0], Double.parseDouble(row[1]), i);

                }

                j = j + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader1.close();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    public void addCalibration(double gradient, double intercept, int id) {
        boolean exists = babyrepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Baby with ID: " + id + "does not exist.");
        }

        Optional<Baby> opt = babyrepository.getBabyById(id);

        if(opt.isPresent()) {
            opt.get().setCali_grad(gradient);
            opt.get().setCali_intercept(intercept);
            babyrepository.save(opt.get());
        }

        else {
            System.out.println("NOT FOUND");
        }

    }

    public void addDelay(double delay, String start_date, String end_date, int id) {
        boolean exists = babyrepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Baby with ID: " + id + "does not exist.");
        }

        Optional<Baby> opt = babyrepository.getBabyById(id);

        if(opt.isPresent()) {
            opt.get().setDelay(delay);
            babyrepository.save(opt.get());
        }

        else {
            System.out.println("NOT FOUND");
        }
    }
}

package com.project.neo.Baby;

import com.project.neo.BabyRepository.Babyrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//This class contains the business functions
@Service
public class BabyService {
    private final Babyrepository babyrepository;
    private List<String> timeStamps = new ArrayList<>();
    private List<String> currentValues = new ArrayList<>();
    private DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private DateTimeFormatter df2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");


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

    private void checkIfBabyExistsInDatabase(int id) {
        boolean exists = babyrepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Baby with Id: " + id + " does not exist.");
        }
    }

    public void deleteBaby(int id) {
        checkIfBabyExistsInDatabase(id);
        babyrepository.deleteById(id);
        System.out.println("Baby Id: " + id + " has been deleted.");
    }

    public void add_NoteTimeStamp(String time_instant, String note, Integer id) {
        checkIfBabyExistsInDatabase(id);

        Optional<Baby> opt = babyrepository.getBabyById(id);

        if (opt.isPresent()) {
            opt.get().getNoteTimestamp().put(time_instant, note);
            babyrepository.save(opt.get());
        } else {
            System.out.println("NOT FOUND");
        }

    }

    public void add_PrickTimeStamp(String time_instant, double prick_data, int id) {
        checkIfBabyExistsInDatabase(id);

        Optional<Baby> opt = babyrepository.getBabyById(id);

        if (opt.isPresent()) {
            LocalDateTime period = LocalDateTime.parse(time_instant, df);
            opt.get().getPrickTimestamp().put(period, prick_data);
            babyrepository.save(opt.get());
        } else {
            System.out.println("NOT FOUND");
        }
    }

    public void add_SweatTimeStamp(int id) {
        checkIfBabyExistsInDatabase(id);


        Optional<Baby> opt = babyrepository.getBabyById(id);

        if (opt.isPresent()) {
            //starts from where it last ended
            for (int i = opt.get().getPrev_point()-1; i < timeStamps.size(); i++) {

                LocalDateTime period = LocalDateTime.parse(timeStamps.get(i), df); //period stores the date and time of the reading that is currently being stored.
                String[] fulldate = split(timeStamps.get(i), ' '); //splits date at the space character


                LocalDate current = LocalDate.parse(fulldate[0], df2); //stores only dd/MM/yyyy part of the date


                //if there is a delay
                if (opt.get().getDelay().containsKey(current)) {
                    period = period.minusMinutes(opt.get().getDelay().get(current));
                }

                //if a calibration has been done that day will search for the changed calibration values
                if (opt.get().getCali_intercept().containsKey(current) && opt.get().getCali_grad().containsKey(current)) {
                    opt.get().getSweatTimestamp().put(period, ((Double.parseDouble(currentValues.get(i)) - opt.get().getCali_intercept().get(current)) / opt.get().getCali_grad().get(current)));
                }
                //otherwise will just put in default values of 0.2 for intercept and 1.1 for gradient.
                else {
                    opt.get().getSweatTimestamp().put(period, ((Double.parseDouble(currentValues.get(i)) - 0.2) / 1.1));
                }


            }

            babyrepository.save(opt.get());
            opt.get().setPrev_point(timeStamps.size());
            timeStamps.clear();
            currentValues.clear();

        }
    }


    public Optional<Baby> returnSingleBaby(int id) {
        checkIfBabyExistsInDatabase(id);

        UpdateSweatLevels(id); //This will update sweat values found in the csv

        return babyrepository.getBabyById(id);
    }

    //takes the id of the selected baby and retrieves sweat info from the database
    public void UpdateSweatLevels(int i) {
        String file = "C:\\Users\\pouge\\Documents\\"+ i + ".csv"; // -< This is the path where the csv is saved.
//        String file = "C:\\Users\\pouge\\Documents\\124790.csv";
        BufferedReader reader1 = null;
        String line;


        try {
            reader1 = new BufferedReader(new FileReader(file));

            int j = 1;
            while ((line = reader1.readLine()) != null) {

                if (j != 1) {

                    String[] row = split(line, ',');
                    timeStamps.add(row[0]);
                    currentValues.add(row[1]);


                }

                j = j + 1;

            }

            add_SweatTimeStamp(i);


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
        checkIfBabyExistsInDatabase(id);

        Optional<Baby> opt = babyrepository.getBabyById(id);

        if (opt.isPresent()) {
            LocalDate current = LocalDate.now();
            opt.get().getCali_grad().put(current, gradient);
            opt.get().getCali_intercept().put(current, intercept);
            babyrepository.save(opt.get());
        } else {
            System.out.println("NOT FOUND");
        }

    }

    public void addDelay(Long delay, int id) {
        checkIfBabyExistsInDatabase(id);

        Optional<Baby> opt = babyrepository.getBabyById(id);

        if (opt.isPresent()) {
            LocalDate current = LocalDate.now();
            opt.get().getDelay().put(current, delay);
            babyrepository.save(opt.get());
        } else {
            System.out.println("NOT FOUND");
        }
    }


    public String[] split(String line, Character delimiter) {
        int j = line.indexOf(delimiter);
        String[] result = new String[2];
        result[0] = line.substring(0,j);
        result[1] = line.substring(j+1,line.length());
        return result;

    }
}
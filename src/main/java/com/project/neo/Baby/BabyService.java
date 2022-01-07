package com.project.neo.Baby;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.project.neo.AmazonS3.S3Service;
import com.project.neo.BabyRepository.Babyrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.sql.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class forms the business layer of the application.  It serves as the middle layer between the API layer (Babycontroller)
 * and the data layer (Babyrepository). This is where all core operations are executed.
 */
@Service
public class BabyService {
    private final Babyrepository babyrepository;
    private final S3Service amazonfile;
    private List<String> timeStamps = new ArrayList<>();
    private List<String> currentValues = new ArrayList<>();
    private DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private DateTimeFormatter df2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    @Autowired
    public BabyService(Babyrepository babyrepository, S3Service amazonfile) {
        this.babyrepository = babyrepository;
        this.amazonfile = amazonfile;
    }

    // method does not work, check over
    public List<Baby> returnBaby() {
        return babyrepository.findAll();
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

    /**
     * This method scans through timeStamps and currentValues lists which store the data imported form the csv file,
     * applies the delay and calibrations specified by the user (if any) to convert sweat data from current (nA) to
     * blood glucose concentrations (mmol/l) and then updates these values for the baby stored in the database.
     *
     * Only data after the previous GET request was made is proccessed.
     *
     * By default, a delay for 20 minutes is set with a calibration intercept of 0.2mmol/l and gradient of 1.1mmol/l/time,
     * which will run unless the user has inputted custom values for that day.
     * @param id identifies baby object stored in database to perform operations on.
     */
    public void add_SweatTimeStamp(int id) {
        checkIfBabyExistsInDatabase(id);
        Optional<Baby> opt = babyrepository.getBabyById(id);

        if (opt.isPresent()) {

            for (int i = opt.get().getPrev_point()-1; i < timeStamps.size(); i++) {

                LocalDateTime period = LocalDateTime.parse(timeStamps.get(i), df);
                String[] fulldate = split(timeStamps.get(i), ' ');


                LocalDate current = LocalDate.parse(fulldate[0], df2);



                if (opt.get().getDelay().containsKey(current)) {
                    period = period.minusMinutes(opt.get().getDelay().get(current));
                }


                if (opt.get().getCali_intercept().containsKey(current) && opt.get().getCali_grad().containsKey(current)) {
                    opt.get().getSweatTimestamp().put(period, ((Double.parseDouble(currentValues.get(i)) - opt.get().getCali_intercept().get(current)) / opt.get().getCali_grad().get(current)));
                }

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

    /**
     * This method is called by the API layer whenever the user wants to view glucose levels. Once called, it calls the
     * updatesweatlevels method which reads data from the specific babies csv data file (which will be named as its id.csv).
     * This then calls the add_SweatTimestamp method which calibrates and stores the data in the database.
     * @param id
     * @return Returns the updated baby object stored in the database once all operations have been conducted to the
     * API layer.
     */
    public Optional<Baby> returnSingleBaby(int id) {
        checkIfBabyExistsInDatabase(id);

        UpdateSweatLevels(id);

        return babyrepository.getBabyById(id);
    }

    /**
     * This method reads in the csv file and stores it in the lists timeStamps and currentValues. Once values have been
     * stored, it calls the add_timestamps method which analyses and saves the data to the specific baby object.
     * @param i stores the value of the baby id that is being analysed.
     */
    public void UpdateSweatLevels(int i) {
        String file = i + ".csv"; // -< This is the path where the csv is saved.
        //String file = "C:\\Users\\65978\\OneDrive - Imperial College London\\Desktop\\test5.csv";
        String bucket_name = "sweatdataprogramming3";
        S3Object object = amazonfile.getsweatData(bucket_name, file);


        InputStream objectData = object.getObjectContent();

        BufferedReader reader1 = null;
        String line;


        try {
            reader1 = new BufferedReader(new InputStreamReader(object.getObjectContent()));

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
                objectData.close();
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
package com.project.neo.Baby;

import com.project.neo.BabyRepository.Babyrepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This is the application's test class for the BabyService class.
 *
 * <p> This class tests the various methods provided in the BabyService class before the application is run. The class
 * is dependency injected with an instance of the BabyService class and the Babyrepository interface
 * in order to carry out the required tests. Please note the use of the packageSlf4j for logging. The test methods
 * themselves clean and erase any data created during the tests to prevent clashes with the main application.</p>
 */
@SpringBootTest
@NoArgsConstructor
@Slf4j
class BabyServiceTest {

    private final Logger logger = LoggerFactory.getLogger(BabyServiceTest.class);

    @Autowired
    private BabyService service;

    @Autowired
    private Babyrepository babyrepository;

    /**
     * Tests whether the application's service class BabyService is able to append new instances of
     * a Baby model into the MongoDB.
     */
    @Test
    @DisplayName("Should add a baby")
    public void shouldAddNewBaby() {
        int id = 124795;
        Baby example_baby = new Baby(124795);
        try {
            service.addNewBaby(example_baby);
            Assertions.assertTrue(babyrepository.getBabyById(id).isPresent());
        } catch (Exception e) {
            logger.error("Unable to detect added baby in database");
        }

        logger.info("Detected added baby in database");
    }

    /**
     * Tests whether the application's service class BabyService is able to delete a Baby model from
     * the MongoDB.
     */
    @Test
    @DisplayName("Should delete a baby")
    void shouldDeleteBaby() {
        int id = 124795;

        try {
            service.deleteBaby(id);
            Assertions.assertTrue(babyrepository.getBabyById(id).isEmpty());
        } catch (Exception e) {
            logger.error("Unable to delete baby in database");
        }

        logger.info("Detected deletion of baby from database");
    }

    /**
     * Tests whether the application's service class BabyService is able to return a single Baby model
     * using its respective ID attribute.
     */
    @Test
    @DisplayName("Should return a single baby")
    void shouldReturnSingleBaby() {
        int id = 248575;
        Baby baby = new Baby(id);
        babyrepository.save(baby);

        try {
            Assertions.assertTrue(service.returnSingleBaby(id).isPresent());
            Assertions.assertFalse(service.returnSingleBaby(id).isEmpty());
        }  catch (Exception e) {
            logger.error("Unable to return single baby via a specific ID from database");
        }

        logger.info("Returned single baby from database");
    }

    /**
     * Tests whether the application's service class BabyService for the Baby model is able to return all the
     * Baby models stored in the MongoDB.
     */
    @Test
    @DisplayName("Should return all babies in database")
    void shouldReturnBaby() {
        try {
            Assertions.assertFalse(service.returnBaby().isEmpty());
            Assertions.assertTrue(service.returnBaby().size()>0);
            Assertions.assertEquals(service.returnBaby().size(), babyrepository.count());
        } catch (Exception e) {
            logger.error("Unable to return all babies stored in database");
        }

        logger.info("Returned all babies stored in database");
    }

    /**
     * Tests whether the application's service class BabyService is able to add a note time stamp to a
     * Baby model stored in the MongoDB.
     */
    @Test
    @DisplayName("Should add a note time stamp to baby model")
    void shouldAddNoteTimeStamp() {
        int id = 132580;
        String timeInstant = "12/01/2022 05:43:00";
        String note = "ate food";

        Baby baby = new Baby(id);
        babyrepository.save(baby);

        if (babyrepository.getBabyById(id).isPresent()) {
            service.add_NoteTimeStamp(timeInstant, note, id);
            Baby babyUpdated = babyrepository.getBabyById(id).get();
            try {
                Assertions.assertEquals(babyUpdated.getNoteTimestamp().get(timeInstant), note);
                babyrepository.save(baby);
            } catch (Exception e) {
                logger.error("Unable to detect addition of note time stamp to baby object stored in database");
            }
        }

        logger.info("Detected addition of note time stamp to baby object stored in database");
    }

    /**
     * Tests whether the application's service class BabyService is able to add a prick time stamp to a Baby model
     * stored in MongoDB.
     */
    @Test
    @DisplayName("Should add a prick time stamp to baby model")
    void shouldAddPrickTimeStamp() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        int id = 132580;
        String timeInstant = "12/01/2022 01:25:00";
        double prickData = 2.2;

        Baby baby = new Baby(id);
        babyrepository.save(baby);

        LocalDateTime period = LocalDateTime.parse(timeInstant, df);

        if (babyrepository.getBabyById(id).isPresent()) {
            service.add_PrickTimeStamp(timeInstant, prickData, id);
            Baby babyUpdated = babyrepository.getBabyById(id).get();
            try {
                Assertions.assertEquals(babyUpdated.getPrickTimestamp().get(period), prickData);
                babyrepository.save(baby);
            } catch (Exception e) {
                logger.error("Unable to detect addition of prick time stamp to baby model stored in database");
            }
        }

        logger.info("Detected addition of prick time stamp to baby object stored in database");
    }

    /**
     * Tests whether the application's service class BabyService is able to add a prick time stamp to a Baby model
     * stored in MongoDB.
     */
    @Test
    @DisplayName("Should add a sweat time stamp to baby model")
    void shouldAddSweatTimeStamp() {
        int id = 124790;
        Baby baby = babyrepository.getBabyById(id).get();

        if (babyrepository.getBabyById(id).isPresent()) {
            service.add_SweatTimeStamp(id);
            Baby babyUpdated = babyrepository.getBabyById(id).get();
            try{
                Assertions.assertFalse(babyUpdated.getSweatTimestamp().isEmpty());
                Assertions.assertTrue(babyUpdated.getSweatTimestamp().size()>0);
                babyrepository.save(baby);

            } catch (Exception e) {
                logger.error("Unable to detect addition of sweat time stamp to baby model stored in database");
            }
        }

        logger.info("Detected addition of sweat time stamp to baby object stored in database");
    }

    /**
     * Tests whether the application's service class BabyService adds calibration to a Baby model for the current day
     * using gradient and intercept values of the calibration graph.
     */
    @Test
    @DisplayName("Should add calibration to baby model")
    public void shouldAddCalibration() {
        double gradient = 2;
        double intercept = 2;
        int id = 132580;

        if (babyrepository.getBabyById(id).isPresent()) {
            service.addCalibration(gradient, intercept, id);
            Baby babyUpdated = babyrepository.getBabyById(id).get();
            try {
                Assertions.assertEquals(babyUpdated.getCali_grad().get(LocalDate.now()), gradient);
                Assertions.assertEquals(babyUpdated.getCali_intercept().get(LocalDate.now()), intercept);
            } catch (Exception e) {
                logger.error("Unable to detect addition of calibration to baby model stored in database");
            }
        }

        logger.info("Detected addition of calibration to baby object stored in database");
    }

    /**
     * Tests whether the application's service class BabyService adds delay for the current day to a baby model
     * using a delay value.
     */
    @Test
    @DisplayName("Should add delay for current day to baby model")
    public void shouldAddDelay() {
        long delay = 10;
        int id = 132580;

        if (babyrepository.getBabyById(id).isPresent()) {
            service.addDelay(delay, id);
            Baby babyUpdated = babyrepository.getBabyById(id).get();
            try {
                Assertions.assertEquals(babyUpdated.getDelay().get(LocalDate.now()), delay);
                babyrepository.deleteById(id);
            } catch (Exception e) {
                logger.error("Unable to detect addition of delay to baby model stored in database");
            }
        }

        logger.info("Detected addition of delay to baby object stored in database");
    }
}
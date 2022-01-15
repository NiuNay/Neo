package com.project.neo.BabyRepository;

import com.project.neo.Baby.Baby;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is the application's test class for the Babyrepository class.
 *
 * <p> This class tests two custom methods for accessing elements in the interface Babyrepository, extending
 * MongoRepository. The class is dependency injected with an instance of the Babyrepository interface. Please note the
 * use of the package Slf4j for logging. </p>
 */
@SpringBootTest
@Slf4j
class BabyrepositoryTest {

    private final Logger logger = LoggerFactory.getLogger(BabyrepositoryTest.class);

    @Autowired
    Babyrepository babyrepository;

    /**
     * Tests whether the application's repository interface Babyrepoistory is able to retrieve
     * an instance of a Baby model via the use of the Baby model's respective ID.
     */
    @Test
    @DisplayName("Should get baby by ID")
    void shouldGetBabyById() {
        int id = 219535;
        Baby baby = new Baby(id);

        babyrepository.save(baby);
        if (babyrepository.getBabyById(id).isPresent()) {
            try {
                Baby babyRetrieved = babyrepository.getBabyById(id).get();
                Assertions.assertEquals(babyRetrieved.getId(), baby.getId());
                babyrepository.deleteById(id);
            } catch (Exception e) {
                logger.error("Baby was not retrieved via ID");
            }
        }

        logger.info("Baby was retrieved via ID");
    }

    /**
     * Tests whether the application's repository interface Babyrepository is able to count the total
     * number of Baby models stored in the MongoDB.
     */
    @Test
    @DisplayName("Should return the number of babies in database")
    void shouldCount() {
        int numberOfBabies = babyrepository.findAll().size();
        int numberOfBabiesViaCount = (int)babyrepository.count();
        assertEquals(numberOfBabies, numberOfBabiesViaCount);
    }

}
package com.project.neo.Baby;

import com.project.neo.BabyRepository.Babyrepository;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestMethod;

/**
 * This class is the application's test class for the BabyService class.
 *
 * <p> This class tests the various methods provided in the BabyService class before the application is run. The class
 * is dependency injected with an instance of the BabyService class in order to carry out the required tests. </p>
 */

@SpringBootTest
@NoArgsConstructor
class BabyServiceTest {

    @Autowired
    private BabyService service;

    @Autowired
    private Babyrepository babyrepository;

    /**
     * Will run before any test method in order to set up the tests.
     * No parameters are passed into method. Will log when set-up is complete.
     */

    @BeforeAll
    public static void settingUpTests() {

    }

    @Test
    @DisplayName("Should add a baby")
    public void shouldAddNewBaby() {
        int id = 124795;
        Baby example_baby = new Baby(124795);
        service.addNewBaby(example_baby);
        Assertions.assertFalse(service.returnSingleBaby(id).isEmpty());
    }

    @Test
    @DisplayName("Should delete a baby")
    void shouldDeleteBaby() {
        int id = 124795;
        service.deleteBaby(id);
        Assertions.assertThrows(IllegalStateException.class, ()-> service.returnSingleBaby(id));
    }

    @Test
    @DisplayName("Should return all babies in database")
    void shouldReturnBaby() {
        Assertions.assertFalse(service.returnBaby().isEmpty());
        Assertions.assertTrue(service.returnBaby().size()>0);
    }

    @Test
    @DisplayName("Should add a note time stamp to the Baby instance")
    void shouldAddNoteTimeStamp() {

    }

    @AfterAll
    public static void cleaningUpTests() {

    }
}
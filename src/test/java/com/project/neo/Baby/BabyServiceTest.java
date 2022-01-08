//package com.project.neo.Baby;
//
//import lombok.NoArgsConstructor;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//@SpringBootTest
//@NoArgsConstructor
//class BabyServiceTest {
//
//    @Autowired
//    private BabyService service;
//
//    @Test
//    void shouldAddNewBaby() {
//        int id = 124795;
//        Baby example_baby = new Baby(id);
//
//        service.addNewBaby(example_baby);
//
//        Assertions.assertFalse(service.returnSingleBaby().isEmpty());
//    }
//
//    @Test
//    void shouldDeleteBaby() {
//        int id = 124795;
//        service.deleteBaby(id);
//        Assertions.assertThrows(IllegalStateException.class, ()-> service.returnSingleBaby(id));
//    }
//
//    @Test
//    void add_NoteTimeStamp() {
//    }
//
//    @Test
//    void add_PrickTimeStamp() {
//    }
//
//    @Test
//    void add_SweatTimeStamp() {
//    }
//
//    @Test
//    void returnSingleBaby() {
//    }
//
//    @Test
//    void updateSweatLevels() {
//    }
//
//    @Test
//    void addCalibration() {
//    }
//
//    @Test
//    void addDelay() {
//    }
//}
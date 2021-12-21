package com.project.neo.Baby;

//This class contains the business functions
public class BabyService {

    public String returnBaby() {
        Baby newBaby = new Baby("149246 baby has been made using service");
        return newBaby.getID();
    }

}

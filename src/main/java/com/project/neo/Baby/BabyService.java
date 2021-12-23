package com.project.neo.Baby;

import com.project.neo.BabyRepository.Babyrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

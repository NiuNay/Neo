package com.project.neo.Baby;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//this class contains all resources for api layer - what will be returned on the server
@RestController
@RequestMapping
public class BabyController {
    //private final BabyService serviceOne;
    BabyService serviceOne = new BabyService();

    /*public BabyController(BabyService serviceOne) {
        this.serviceOne = serviceOne;
    }*/
    @GetMapping //makes this a restful end point by default
    public String returnBabyID() {
        return serviceOne.returnBaby();
    }


}

package com.project.neo.Baby;

import com.project.neo.BabyRepository.Babyrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//this class contains all resources for api layer - what will be returned on the server
@RestController //defines this as the api layer - which communicates directly with client/server
@RequestMapping//(path="/api") //sets the url where the end points will be returned
public class BabyController {
    private final BabyService service;

    @Autowired //This tells the program to make a dependency injection for BabyService obj. BabyService class has been annotated with @service so it knows where to find it
    public BabyController(BabyService service) {
        this.service = service;
    }

    @GetMapping //makes this a restful end point by default so this is what is returned to the server
    public List<Baby> returnBabyID() {
        return service.returnBaby();
    }

    @PostMapping(path = "/addbaby")
    public void addNewBaby(@RequestBody Baby baby) { //from the request body of the client map it to a baby
        service.addNewBaby(baby);
        System.out.println("Baby added.");
    }

    @DeleteMapping(path = "/{id}")
    public void deleteBaby(@PathVariable("id") int id) {
        service.deleteBaby(id);
    }


}

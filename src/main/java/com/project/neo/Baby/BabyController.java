package com.project.neo.Baby;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jdk8.OptionalDoubleSerializer;
import com.project.neo.BabyRepository.Babyrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

//this class contains all resources for api layer - what will be returned on the server
@CrossOrigin(origins = "http://localhost:3000")
@RestController //defines this as the api layer - which communicates directly with client/server
@RequestMapping//(path="/api") //sets the url where the end points will be returned
public class BabyController {
    private final BabyService service;

    @Autowired //This tells the program to make a dependency injection for BabyService obj. BabyService class has been annotated with @service so it knows where to find it
    public BabyController(BabyService service) {
        this.service = service;
    }

    @GetMapping //makes this a restful end point by default so this is what is returned to the server. Returns full list of all babies in db
    public List<Baby> returnBaby() {
        return service.returnBaby();
    }



    @PostMapping(path = "/addBaby") //adds baby
    public void addNewBaby(@RequestBody Baby baby) { //from the request body of the client map it to a baby
        service.addNewBaby(baby);
        System.out.println("Baby added.");
    }

    /*@GetMapping(path = "/{id}") //returns only the specified baby
    public List<Optional> returnSingleBaby(@PathVariable("id") int id) {
        List <Optional> baby = new ArrayList<>();
        baby.add(service.returnSingleBaby(id));
        return baby;
    }*/

    @GetMapping(path = "/{id}") //returns only the specified baby
    public List<Object> returnTimestamps(@PathVariable("id") int id) {
        Baby baby;
        List<Object> tobereturned = new ArrayList<>();
        baby = service.returnSingleBaby(id).get();
        Set timevalues = baby.getSweatTimestamp().keySet();
        Collection concentrations = baby.getSweatTimestamp().values();
        Set pricktime = baby.getPrickTimestamp().keySet();
        Collection prickvalues = baby.getPrickTimestamp().values();
        Set notetime = baby.getNoteTimestamp().keySet();
        Collection notes = baby.getNoteTimestamp().values();
        tobereturned.add(timevalues);
        tobereturned.add(concentrations);
        tobereturned.add(pricktime);
        tobereturned.add(prickvalues);
        tobereturned.add(notetime);
        tobereturned.add(notes);
        return tobereturned;
    }


    @DeleteMapping(path = "/{id}/delete") //deletes baby by id
    public void deleteBaby(@PathVariable("id") int id) {
        service.deleteBaby(id);
    }

    //the request body of the post method maps to only one object - objectnode.
    //time instant and note are then extracted separately.
    @PostMapping(path = "/{id}/addNote")
    public void addNewNote(@RequestBody ObjectNode objectNode, @PathVariable("id") int id) {
        String time_instant = objectNode.get("time_instant").asText();
        String note = objectNode.get("note").asText();
        service.add_NoteTimeStamp(time_instant, note, id);
    }

    @PostMapping(path = "/{id}/addPrickData")
    public void addNewPrick(@RequestBody ObjectNode objectNode, @PathVariable("id") int id) {
        String time_instant = objectNode.get("time_instant").asText();
        double prick_data = objectNode.get("prick_data").asDouble();
        service.add_PrickTimeStamp(time_instant, prick_data, id);
    }

    @PostMapping(path = "/{id}/addDelay")
    public void addDelay(@RequestBody ObjectNode objectNode, @PathVariable("id") int id) {
        Long delay = objectNode.get("delay").asLong();
        service.addDelay(delay, id);
    }

    @PostMapping(path = "/{id}/addCalibration")
    public void addCalibration(@RequestBody ObjectNode objectNode, @PathVariable("id") int id) {
        double gradient = objectNode.get("gradient").asDouble();
        double intercept = objectNode.get("intercept").asDouble();
        service.addCalibration(gradient, intercept, id);
    }


}

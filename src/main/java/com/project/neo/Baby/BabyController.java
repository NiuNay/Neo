package com.project.neo.Baby;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * This class contains all resources for the API layer. It communicates directly with frontend through the different
 * http requests that are annotated at the top of each method representing RESTful endpoints.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping//(path="/api") //sets the url where the end points will be returned
public class BabyController {
    private final BabyService service;

    @Autowired
    public BabyController(BabyService service) {
        this.service = service;
    }

    @GetMapping
    public List<Baby> returnBaby() {
        return service.returnBaby();
    }

    @PostMapping(path = "/addBaby")
    public void addNewBaby(@RequestBody Baby baby) {
        service.addNewBaby(baby);
        System.out.println("Baby added.");
    }

    /**
     * This method will return all fields of the baby object specified by id in order to display all data, including
     * graphing out the sweat and prick data under the "view glucose levels" function in frontend.
     * @param id specifies which baby object to return from the database
     * @return returns baby object as a list of lists - each sublist stores a different field where:
     * 1. Sweat timestamps
     * 2. Sweat values
     * 3. Prick timestamps
     * 4. Prick values
     * 5. Note Timestamps
     * 6. Notes
     */
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
        System.out.println("Returned all fields to frontend");
        return tobereturned;
    }


    @DeleteMapping(path = "/{id}/delete") //deletes baby by id
    public void deleteBaby(@PathVariable("id") int id) {
        service.deleteBaby(id);
    }

    /**
     * This method adds a note be stored in the baby object note hashmap.
     * @param objectNode Stores the JSON request body sent from frontend in object node. In this case, it allows extraction of the values
     *                   specified under "time_instant" and "note".
     * @param id Specifies which patient to edit.
     */
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

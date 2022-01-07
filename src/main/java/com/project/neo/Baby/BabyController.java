package com.project.neo.Baby;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * This class contains all resources for the API layer. It communicates directly with frontend through the different
 * http requests that are annotated at the top of each method representing RESTful endpoints.
 */
//@CrossOrigin(origins = "https://neo-patient-care.herokuapp.com/")
@CrossOrigin(origins = "http://localhost:3000/")
@RestController //defines this as the api layer - which communicates directly with client/server
@RequestMapping//(path="/api") //sets the url where the end points will be returned
public class BabyController {
    private final BabyService service;

    /**
     * Constructor conducts a dependency injection of the baby service object.
     * @param service The BabyService object to be injected in to the controller class.
     */
    @Autowired
    public BabyController(BabyService service) {
        this.service = service;
    }

    /**
     * This method returns a list of all baby objects currently in the MongoDB database.
     * @return List of all baby objects in the database.
     */
    @GetMapping
    public List<Baby> returnBaby() {
        return service.returnBaby();
    }

    /**
     * This method allows the user to add new babies into the system. This URL will not be accessible from the frontend
     * to control who is able to add new babies into the system. To add a baby, the user will send a POST request to
     * this URL with the body specifying the fields of the baby to be added, such as id and previous data.
     * @param baby Baby object to be added.
     */
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

    /**
     * This method deletes a baby by id that is currently stored in the database.
     * @param id Id of baby to be deleted.
     */
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

    /**
     * This method allows users to add a prick data at a specific time instant to a specific baby stored in the database.
     * @param objectNode Stores the JSON request body sent from frontend in object node. In this case, it allows extraction of the values
     *                  specified under "time_instant" and "prick_data".
     * @param id Id of baby to store prick data in.
     */
    @PostMapping(path = "/{id}/addPrickData")
    public void addNewPrick(@RequestBody ObjectNode objectNode, @PathVariable("id") int id) {
        String time_instant = objectNode.get("time_instant").asText();
        double prick_data = objectNode.get("prick_data").asDouble();
        service.add_PrickTimeStamp(time_instant, prick_data, id);
    }

    /**
     * This method allows users to add a delay for the current day into a specific baby stored in the database.
     * @param objectNode Stores the JSON request body sent from frontend in object node. In this case, it allows extraction of the values
     *                   specified under "delay".
     * @param id Id of baby to add delay to.
     */
    @PostMapping(path = "/{id}/addDelay")
    public void addDelay(@RequestBody ObjectNode objectNode, @PathVariable("id") int id) {
        Long delay = objectNode.get("delay").asLong();
        service.addDelay(delay, id);
    }

    /**
     * This method allows users to add a calibration for the current day to a specific baby stored in the database.
     * @param objectNode Stores the JSON request body sent from frontend in object node. In this case, it allows extraction of the values
     *                   specified under "gradient" and "intercept".
     * @param id Id of baby to add calibration to.
     */
    @PostMapping(path = "/{id}/addCalibration")
    public void addCalibration(@RequestBody ObjectNode objectNode, @PathVariable("id") int id) {
        double gradient = objectNode.get("gradient").asDouble();
        double intercept = objectNode.get("intercept").asDouble();
        service.addCalibration(gradient, intercept, id);
    }


}

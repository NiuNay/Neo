package com.project.neo.Baby;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

//needs function to take data from excel sheet continuously
//set and get methods
@Document(collection = "Babies") //for MongoDB to recognise which collection this is for
public class Baby {
    @Id //prevents spring from generating its own id
    private String ID;
    private HashMap< String, String> noteTimestamp = new HashMap<String,String>();
    private HashMap< String, String> prickTimestamp = new HashMap<String,String>();
    private HashMap< String, String> sweatTimestamp = new HashMap<String,String>();

    public Baby (String ID) {
        this.ID = ID;
    }
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }



}

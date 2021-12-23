package com.project.neo.Baby;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

//needs function to take data from excel sheet continuously
//set and get methods
@ToString
@Document(collection = "Babies") //for MongoDB to recognise which collection this is for
public class Baby {
    @Id //prevents spring from generating its own id
    private int ID;
    private HashMap< Integer, String> noteTimestamp = new HashMap<Integer,String>();
    private HashMap< Integer, String> prickTimestamp = new HashMap<Integer,String>();
    private HashMap< Integer, String> sweatTimestamp = new HashMap<Integer,String>();

    public Baby (int ID) {
        this.ID = ID;
    }
    public Baby () {
    }
    public Baby (Baby baby) {
        this.ID = baby.getID();
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }



}

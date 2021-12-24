package com.project.neo.Baby;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.HashMap;
import java.time.Instant;

//needs function to take data from excel sheet continuously
//set and get methods
@ToString
@Document(collection = "Babies") //for MongoDB to recognise which collection this is for
public class Baby {
    @Id @Getter @Setter private int ID;
    @Getter @Setter private HashMap< Instant, String> noteTimestamp = new HashMap<>();
    @Getter @Setter private HashMap< Instant, BigDecimal> prickTimestamp = new HashMap<>();
    @Getter @Setter private HashMap< Instant, BigDecimal> sweatTimestamp = new HashMap<>();

    public Baby(int ID) {
        this.ID = ID;
    }

    public Baby(Baby baby) {
        this.ID = baby.getID();
        this.noteTimestamp = baby.getNoteTimestamp();
        this.prickTimestamp = baby.getPrickTimestamp();
        this.sweatTimestamp = baby.getSweatTimestamp();
    }
}

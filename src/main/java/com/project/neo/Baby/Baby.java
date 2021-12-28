package com.project.neo.Baby;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.LinkedHashMap;

//needs function to take data from excel sheet continuously
//set and get methods
@ToString
@Document(collection = "Babies") //for MongoDB to recognise which collection this is for
@NoArgsConstructor
public class Baby {
    @Id @Getter @Setter private Integer id;
    @Getter @Setter private double cali_grad = 1.1;
    @Getter @Setter private double cali_intercept = 0.2;
    @Getter @Setter private double delay = 20; //in minutes
    @Getter @Setter private LinkedHashMap< String, String> noteTimestamp = new LinkedHashMap<>();
    @Getter private LinkedHashMap< String, Double> prickTimestamp = new LinkedHashMap<>();
    @Getter private LinkedHashMap< String, Double> sweatTimestamp = new LinkedHashMap<>();

    public Baby(Integer ID) {
        this.id = ID;
    }

    public Baby(Baby baby) {
        this.id = baby.getId();
        this.noteTimestamp = baby.getNoteTimestamp();
        this.prickTimestamp = baby.getPrickTimestamp();
        this.sweatTimestamp = baby.getSweatTimestamp();
    }

    public Integer getId() {
        return id;
    }


}

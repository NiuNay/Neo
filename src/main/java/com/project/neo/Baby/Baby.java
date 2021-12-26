package com.project.neo.Baby;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

//needs function to take data from excel sheet continuously
//set and get methods
@ToString
@Document(collection = "Babies") //for MongoDB to recognise which collection this is for
@NoArgsConstructor
public class Baby {
    @Id @Getter @Setter private Integer id;
    @Getter @Setter private double cali_grad;
    @Getter @Setter private double cali_intercept;
    @Getter @Setter private HashMap< String, String> noteTimestamp = new HashMap<>();
    @Getter private HashMap< String, Double> prickTimestamp = new HashMap<>();
    @Getter private HashMap< String, String> sweatTimestamp = new HashMap<>();

    public Baby(Integer ID) {
        this.id = ID;
    }

    public Baby(Baby baby) {
        this.id = baby.getId();
        this.noteTimestamp = baby.getNoteTimestamp();
        this.prickTimestamp = baby.getPrickTimestamp();
        this.sweatTimestamp = baby.getSweatTimestamp();
    }
}

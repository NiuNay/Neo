package com.project.neo.Baby;
import lombok.*;
import org.apache.tomcat.jni.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;

//needs function to take data from excel sheet continuously
//set and get methods
@ToString
@Document(collection = "Babies") //for MongoDB to recognise which collection this is for
@NoArgsConstructor
public class Baby {
    @Id @Getter @Setter private Integer id;
    @Getter private LinkedHashMap<LocalDate, Double> cali_grad = new LinkedHashMap<>();
    @Getter private LinkedHashMap<LocalDate, Double> cali_intercept = new LinkedHashMap<>();
    @Getter private LinkedHashMap<LocalDate, Long> delay = new LinkedHashMap<>(); //in minutes
    @Getter @Setter private LinkedHashMap< String, String> noteTimestamp = new LinkedHashMap<>();
    @Getter private LinkedHashMap<LocalDateTime, Double> prickTimestamp = new LinkedHashMap<>();
    @Getter private LinkedHashMap<LocalDateTime, Double> sweatTimestamp = new LinkedHashMap<>();
    @Getter @Setter int prev_point = 1; //stores the value of where the previous reading was stored (from the last time the GET request was sent)

    public Baby(Integer ID) {
        this.id = ID;
    }

    public Baby(Baby baby) {
        this.id = baby.getId();
        this.noteTimestamp = baby.getNoteTimestamp();
        this.prickTimestamp = baby.getPrickTimestamp();
        this.sweatTimestamp = baby.getSweatTimestamp();
        this.cali_grad = baby.getCali_grad();
        this.cali_intercept = baby.getCali_intercept();
        this.delay = baby.getDelay();
    }
}

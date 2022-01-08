package com.project.neo.NeoUserSystem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Roles")
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter @Setter
    private String name;
}

package com.project.neo.NeoUserSystem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Document(collection = "Users")
@NoArgsConstructor
@AllArgsConstructor
public class NeoUser {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Getter @Setter private int id;
    @Getter @Setter private String username;
    @Getter @Setter private String password;
    @ManyToMany(fetch = FetchType.EAGER) // loads the roles for each user
    @Getter @Setter private Collection<Role> roles = new ArrayList<>();
}

package com.project.neo.Role;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="roles")
public class Role {

    @Id @Getter @Setter
    private String id;

    @Getter @Setter
    private String role;
}

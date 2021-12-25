package com.project.neo.User.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id @Getter @Setter private Integer id;
    @Getter @Setter private String password;
}

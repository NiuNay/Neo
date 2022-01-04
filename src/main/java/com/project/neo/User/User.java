//package com.project.neo.User;
//
//import com.project.neo.Role.Role;
//import lombok.*;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.DBRef;
//import org.springframework.data.mongodb.core.mapping.Document;
//import java.util.Set;
//
//@Document(collection = "Users")
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//public class User {
//    @Id @Getter @Setter private int id;
//    @Getter @Setter private String username;
//    @Getter @Setter private String password;
//    @Getter @Setter private boolean enabled;
//
//    @DBRef @Getter @Setter
//    private Set<Role> roles;
//
//    public User(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }
//
//    public User(int id, String username, String password) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//    }
//
//    public User(int id, String username, String password, boolean enabled) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.enabled = enabled;
//    }
//}

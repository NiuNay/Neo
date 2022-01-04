//package com.project.neo.User;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@CrossOrigin(origins = "http://localhost:3000")
//@RestController
//@RequestMapping(path="api/users")
//public class UserController {
//    private final UserService service;
//
//    @Autowired
//    public UserController(UserService service) {
//        this.service = service;
//    }
//
//    @GetMapping
//    public List<User> returnUserID() {
//        return service.returnUser();
//    }
//
//    @PostMapping(path = "/adduser")
//    public void addNewUser(@RequestBody User user) {
//        service.addNewUser(user);
//        System.out.println("User added.");
//    }
//
//    @DeleteMapping(path = "/{id}")
//    public void deleteUser(@PathVariable("id") int id) {
//        service.deleteUser(id);
//    }
//}

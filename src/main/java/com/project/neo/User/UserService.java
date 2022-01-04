//package com.project.neo.User;
//
//import com.project.neo.Role.RoleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class UserService implements UserDetailsService {
//    private final UserRepository user_repository;
//    private final RoleRepository role_Repository;
//
//    @Autowired
//    public UserService(UserRepository user_repository, RoleRepository role_Repository) {
//        this.user_repository = user_repository;
//        this.role_Repository = role_Repository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user_found = user_repository.findByUsername(username);
//
//        if (user_found == null) {
//            return null;
//        }
//
//        String name = user_found.getUsername();
//        String pwd = user_found.getPassword();
//
//        return new org.springframework.security.core.userdetails.User(name, pwd, new ArrayList<>());
//    }
//
//    public List<User> returnUser() {
//        return user_repository.findAll();
//    }
//
//    public void addNewUser(User user) {
//        System.out.println(user);
//        user_repository.save(user);
//        System.out.println("user saved");
//    }
//
//    public void deleteUser(Integer id) {
//        boolean exists = user_repository.existsById(id);
//        if (!exists) {
//            throw new IllegalStateException("Baby with Id: " + id + " does not exist.");
//        }
//        user_repository.deleteById(id);
//        System.out.println("Baby Id: " + id + " has been deleted.");
//    }
//}

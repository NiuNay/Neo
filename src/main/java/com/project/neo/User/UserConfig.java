//package com.project.neo.User;
//
//import com.project.neo.Role.RoleRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class UserConfig {
//
//    @Bean
//    CommandLineRunner commandLineRunnerUser(UserRepository userRepository, RoleRepository roleRepository) {
//        return args -> {
//            UserService service = new UserService(userRepository, roleRepository);
//
//            int int1 = 1;
//            String username1 = "user_1";
//            String password1 = "test";
//
//            User user1 = new User(int1, username1, password1, true);
//
//            userRepository.save(user1);
//        };
//    }
//}

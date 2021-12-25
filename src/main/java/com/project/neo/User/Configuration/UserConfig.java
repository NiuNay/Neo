package com.project.neo.User.Configuration;

import com.project.neo.User.Model.User;
import com.project.neo.User.Repository.UserRepository;
import com.project.neo.User.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunnerUser(UserRepository userRepository) {
        return args -> {
            UserService service = new UserService(userRepository);

            Integer int1 = 1;
            String password1 = "test";

            User user1 = new User(int1, password1);

            userRepository.save(user1);
        };
    }
}

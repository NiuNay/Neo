package com.project.neo;

import com.project.neo.NeoUserSystem.NeoUser;
import com.project.neo.NeoUserSystem.NeoUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class NeoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(NeoApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {

    }

    @Bean
    CommandLineRunner run (NeoUserService neoUserService) {
        return args -> {
            neoUserService.saveUser(new NeoUser(1, "john", "test", new ArrayList<>()));
        };
    }
}

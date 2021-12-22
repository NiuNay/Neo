package com.project.neo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NeoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(NeoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}

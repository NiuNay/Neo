package com.project.neo.Security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping(value = "hello")
    public String hello() {
        return "Hello world";
    }
}

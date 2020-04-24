package com.campuslive.campusliveserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class CampusliveserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusliveserverApplication.class, args);
    }

    @RequestMapping(value = "/hi")
    public String hello() {
        return "Hello World";
    }
}

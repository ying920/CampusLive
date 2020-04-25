package com.campuslive.campusliveserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 林新宇
 * @Phone 17810204868
 * @email aomiga523@163.com
 */

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

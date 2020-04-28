package com.campuslive.campusliveserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;


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

    //用于测试服务器是否联通，暂无功能性用途
    @RequestMapping(value = "/hi")
    public String hello() {
        return "HelloWorld";
    }

    //用于测试get时间，暂无功能性用途
    @RequestMapping(value = "/get-time")
    public String getTime(){
        Date date = new Date();//获得系统时间.
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        return nowTime;
    }
}

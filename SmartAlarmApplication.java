package com.smartalarmsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.smartalarmsystem")
public class SmartAlarmApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartAlarmApplication.class, args);
        System.out.println("Smart Alarm System started successfully!");
    }
}
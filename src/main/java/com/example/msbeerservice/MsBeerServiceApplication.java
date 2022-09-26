package com.example.msbeerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.msbeerservice"})
public class MsBeerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsBeerServiceApplication.class, args);
    }

}

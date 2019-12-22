package com.mcbanners.mcapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class McAPIApplication {

    public static void main(String[] args) {
        SpringApplication.run(McAPIApplication.class, args);
    }

}

package com.kafein.intern.identity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class PostingerIdentityServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostingerIdentityServiceApplication.class, args);
    }

}

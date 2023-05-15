package com.meta.userpostproject;

import jakarta.annotation.PostConstruct;
import lombok.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Key;

@SpringBootApplication
public class UserPostProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserPostProjectApplication.class, args);
    }

}

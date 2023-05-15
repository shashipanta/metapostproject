package com.meta.userpostproject;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class UserPostProjectApplication {


    public static void main(String[] args) {
        SpringApplication.run(UserPostProjectApplication.class, args);
    }



}

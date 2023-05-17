package com.meta.userpostproject;

import com.meta.userpostproject.readyml.Key;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;


@SpringBootApplication
public class UserPostProjectApplication {
//
//    @Value("${user.created.value1}")
//    public String key1;
//    @Value("${user.created.value1}")
//    public String key2;
//    @Value("${user.created.value3}")
//    public String key3;
//
//
//    @Autowired
//    private Key key;

    public static void main(String[] args) {
        SpringApplication.run(UserPostProjectApplication.class, args);
    }

//    @PostConstruct
//    public void doSomething() {
//        /**
//         * When we need to load something during application start up
//         */
//        System.out.println("I am executed");
//        System.out.println(key1);
//        System.out.println(key2);
//        System.out.println(key3);
//
//        System.out.println(key.toString());
//    }

}

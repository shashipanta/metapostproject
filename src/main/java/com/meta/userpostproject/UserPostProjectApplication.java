package com.meta.userpostproject;

import com.meta.userpostproject.email.GmailSender;
import com.meta.userpostproject.readyml.Key;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.xml.stream.events.Comment;


@SpringBootApplication
public class UserPostProjectApplication implements CommandLineRunner {

    @Value("${user.created.value1}")
    public String key1;
    @Value("${user.created.value1}")
    public String key2;
    @Value("${user.created.value3}")
    public String key3;


    @Autowired
    private Key key;

    @Autowired
    private GmailSender gmailSender;

    public static void main(String[] args) {
        SpringApplication.run(UserPostProjectApplication.class, args);
    }

    @PostConstruct
    public void doSomething() {
        /**
         * When we need to load something during application start up
         */
        System.out.println("I am executed");
        System.out.println(key1);
        System.out.println(key2);
        System.out.println(key3);

        System.out.println(key.toString());
    }




        @Override
        public void run(String... args) throws Exception {
            String to = "eziomanish1@gmail.com";
            String from = "shashipanta57@gmail.com";
            String subject = "Application for Leave";
            String body = "With deep respect, I want to ask you an entire month leave for ....";
            gmailSender.sendEmail(to, from, subject, body);
        }

}

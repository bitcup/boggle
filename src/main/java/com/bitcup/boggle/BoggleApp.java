package com.bitcup.boggle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class BoggleApp {

    public static void main(String[] args) {
        SpringApplication.run(BoggleApp.class, args);
    }

}

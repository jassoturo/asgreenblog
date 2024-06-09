package com.server.springacl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringAclDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAclDemoApplication.class, args);
    }

}

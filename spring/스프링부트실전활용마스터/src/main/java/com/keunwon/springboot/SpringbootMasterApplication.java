package com.keunwon.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.blockhound.BlockHound;

@SpringBootApplication
public class SpringbootMasterApplication {

    public static void main(String[] args) {
        BlockHound.install();

        SpringApplication.run(SpringbootMasterApplication.class, args);
    }

}

package com.myshop;

import com.myshop.common.jpa.RangeableRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableJpaRepositories(repositoryBaseClass = RangeableRepositoryImpl.class)
@SpringBootApplication
public class MyShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyShopApplication.class, args);
    }
}

package com.zipi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(order = 10)
public class RmbMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(RmbMarketApplication.class, args);
    }
}

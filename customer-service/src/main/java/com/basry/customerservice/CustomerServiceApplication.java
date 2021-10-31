package com.basry.customerservice;

import com.basry.customerservice.dto.CustomerRequestDTO;
import com.basry.customerservice.services.CustomerServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerServiceImpl customerService) {
        return args -> {

        };
    }

}

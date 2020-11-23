package com.miller;

import com.miller.domain.entity.Client;
import com.miller.domain.repository.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SellerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SellerApplication.class, args);
    }
}

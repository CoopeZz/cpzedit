package com.coopezz.cpzedit;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@RequiredArgsConstructor
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CpzeditApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CpzeditApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}

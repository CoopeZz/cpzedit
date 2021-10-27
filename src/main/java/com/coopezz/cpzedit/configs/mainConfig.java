package com.coopezz.cpzedit.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class mainConfig {

    @Bean
    BCryptPasswordEncoder getBCryptEncoder () {return new BCryptPasswordEncoder();}
}

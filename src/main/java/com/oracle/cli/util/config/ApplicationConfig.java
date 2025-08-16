package com.oracle.cli.util.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class ApplicationConfig {

    @Bean
    public synchronized Scanner input() {
        return new Scanner(System.in);
    }

    @Bean
    public synchronized ObjectMapper mapper() {
        return new ObjectMapper();
    }
}

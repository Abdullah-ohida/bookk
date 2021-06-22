package com.example.bookk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@SpringBootApplication
public class BookkApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookkApplication.class, args);
    }


//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder templateBuilder){
//        return templateBuilder.setConnectTimeout(Duration.ofSeconds(3))
//                .setReadTimeout(Duration.ofSeconds(3)).build();
//    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

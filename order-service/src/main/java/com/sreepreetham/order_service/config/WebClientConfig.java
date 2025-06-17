package com.sreepreetham.order_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    // This will create a Bean of type WebClient with name webClient
    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }
}

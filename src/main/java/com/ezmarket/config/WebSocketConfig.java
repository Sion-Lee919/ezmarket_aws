package com.ezmarket.config;

import java.io.Console;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;



@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
    	System.out.println("endpoint 설정");
        registry.addEndpoint("/ws")
                //.setAllowedOrigins("*")
        		
        		.setAllowedOriginPatterns("*/**").withSockJS();
    	System.out.println("endpoint 설정종료");

        		
    }
    
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
    	System.out.println("/sub 체크");
        registry.enableSimpleBroker("/sub");
        System.out.println("/sub 체크종료");
        System.out.println("/pub 종착지 체크");
        registry.setApplicationDestinationPrefixes("/pub");
        System.out.println("/pub 종착지 체크종료");
    }
}
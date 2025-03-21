package com.ezmarket.config;

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
    	System.out.println("WebSocketConfig 1.");
        registry.addEndpoint("/ws")
                //.setAllowedOrigins("*")
        		.setAllowedOriginPatterns("*/**");
        		
    }
    
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
    	System.out.println("WebSocketConfig 2.");
    	registry.enableSimpleBroker("/sub");
        registry.setApplicationDestinationPrefixes("/pub");
    }
}
package com.ezmarket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 정적 리소스 처리 경로 제한
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
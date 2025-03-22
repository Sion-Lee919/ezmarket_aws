package com.ezmarket;

import org.apache.tomcat.websocket.server.WsContextListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(EzmarketApplication.class);
	}

	  @Override
	    public void onStartup(ServletContext servletContext) throws ServletException {
	        super.onStartup(servletContext);

	        // 외부 톰캣에서 WebSocket 핸들러 강제 등록
	        servletContext.addListener(new WsContextListener());
	    }

	    public static void main(String[] args) {
	        SpringApplication.run(ServletInitializer.class, args);
	    }
	}
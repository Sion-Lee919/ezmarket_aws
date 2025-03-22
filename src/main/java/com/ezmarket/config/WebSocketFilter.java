package com.ezmarket.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WebSocketFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;

        // WebSocket 요청은 무시하고 다음 필터로 넘김
        if (req.getRequestURI().startsWith("/ws")) {
            chain.doFilter(request, response);
            return;
        }

        // 나머지는 정상 처리
        chain.doFilter(request, response);
    }
}
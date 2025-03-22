package com.ezmarket.config;

import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")  // 모든 요청에 대해 필터링
public class PayloadFilter implements Filter {
	
	//입력필드
    private static final String XSS_INJECTION_PATTERN = "<script>(.*?)</script>|<|>|<.*?>|javascript:|alert\\(|on.*?=.*?>";
    private static final String SQL_INJECTION_PATTERN = "select|insert|update|delete|drop|union|--|\\|\\*|\\*/|;|\\'|\"";
    private static final String INVALID_CHARACTERS_PATTERN = "[<>\"'%;)(&+]";
    private static final String PATH_TRAVERSAL_PATTERN = "(?i)\\.\\.(\\\\|/|%2e%2e)";
    //파일
    private static final String MALWARE_PATTERN = "exe|bat|cmd|sh|php|jsp|asp|cgi|com";
    
    //입력필드
    private static final Pattern xssInjectionPattern = Pattern.compile(XSS_INJECTION_PATTERN, Pattern.CASE_INSENSITIVE);
    private static final Pattern sqlInjectionPattern = Pattern.compile(SQL_INJECTION_PATTERN, Pattern.CASE_INSENSITIVE);
    private static final Pattern invalidCharactersPattern = Pattern.compile(INVALID_CHARACTERS_PATTERN, Pattern.CASE_INSENSITIVE);
    private static final Pattern pathTraversalPattern = Pattern.compile(PATH_TRAVERSAL_PATTERN);
    //파일
    private static final Pattern malwarePattern = Pattern.compile(MALWARE_PATTERN, Pattern.CASE_INSENSITIVE);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        req.getParameterMap().forEach((key, value) -> {
            for (int i = 0; i < value.length; i++) {
                value[i] = cleanXSS(value[i]);
                value[i] = cleanSQLInjection(value[i]);
                value[i] = cleanPathTraversal(value[i]);
                value[i] = cleanMalware(value[i]);
                value[i] = cleanInvalidCharacters(value[i]);
            }
        });

        chain.doFilter(request, response);
    }
    
    //입력필드
    private String cleanXSS(String value) {
        if (value != null) {
            value = xssInjectionPattern.matcher(value).replaceAll("");
        }
        return value;
    }
    
    private String cleanSQLInjection(String value) {
        if (value != null) {
            value = sqlInjectionPattern.matcher(value).replaceAll("");
        }
        return value;
    }
    
    private String cleanPathTraversal(String value) {
        if (value != null) {
            value = pathTraversalPattern.matcher(value).replaceAll("");
        }
        return value;
    }
    
    private String cleanInvalidCharacters(String value) {
        if (value != null) {
            value = invalidCharactersPattern.matcher(value).replaceAll("");
        }
        return value;
    }
    
    //파일
    private String cleanMalware(String value) {
        if (value != null) {
            value = malwarePattern.matcher(value).replaceAll("");
        }
        return value;
    }
}
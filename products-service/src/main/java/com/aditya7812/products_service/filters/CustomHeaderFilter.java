/*package com.aditya7812.products_service.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomHeaderFilter extends OncePerRequestFilter {

    public JwtUtil jwtUtil;

    public CustomHeaderFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        CustomHttpServletRequestWrapper requestWrapper = new CustomHttpServletRequestWrapper(request);
        System.out.println("Here Wrapper");
        System.out.println(request.getHeader("userId"));
  
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader.length() > 0 && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String userId = jwtUtil.getUserIDFromToken(token);
            requestWrapper.addHeader("userId", userId);

        }

        filterChain.doFilter(requestWrapper, response);
    }
}
*/
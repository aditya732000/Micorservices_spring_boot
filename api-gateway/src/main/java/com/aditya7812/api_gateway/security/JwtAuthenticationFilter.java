/*package com.aditya7812.api_gateway.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {
        System.out.println("here");

        try {
            if (isAuthRequest(request)) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = getTokenFromRequest(request);

            if (token == null) {
                System.out.println("Token not present");
                filterChain.doFilter(request, response);
                return;
            }

            String username = jwtUtil.getUsernameFromToken(token);
            System.out.println(username);


            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                
                if (jwtUtil.validateToken(token)) {
                    System.out.println("Validated");

                    List<GrantedAuthority> authorities =  jwtUtil.extractAuthoritiesFromToken(token);
                    System.out.println(authorities);
                    setAuthentication(request, username, authorities);
                    String userId = jwtUtil.getUserIDFromToken(token);
                    System.out.println(userId);
                    //requestWrapper.addHeader("X-UserId", userId);

                }
            }

            filterChain.doFilter(request, response);
        } catch (MalformedJwtException e) {
            handleErrorToken(response, "Malformed JWT: " + e.getMessage());
        } catch (JwtException e) {
            handleErrorToken(response, "JWT Exception: " + e.getMessage());
        }
    }

    
    private boolean isAuthRequest(HttpServletRequest request) {
        return request.getServletPath().contains("/api/auth");
    }


    private String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println("---------------------------------------------------");
        System.out.println(header);

        if (header != null && header.startsWith("Bearer ")) {
            return header.replace("Bearer ", "");
        }

        return null;
    }

   
    
    private void setAuthentication(HttpServletRequest request, String username, List<GrantedAuthority> authorities) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username,
                null,
                authorities
        );
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    
    private void handleErrorToken(HttpServletResponse response, String error) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"" + error + "\"}");
    }
}*/
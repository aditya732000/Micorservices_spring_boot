package com.aditya7812.api_gateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


import javax.crypto.SecretKey;

@Service
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;  // Change this to a secure key

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String getUsernameFromToken(String token) {
        return extractAllClaims(token).getSubject();
    }


    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    
    public boolean validateToken(String token) {
        try {
            return extractAllClaims(token).getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public List<GrantedAuthority> extractAuthoritiesFromToken(String token) {
        Claims claims = extractAllClaims(token);
        String role = claims.get("role").toString();
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));
        return authorities;
    }
}

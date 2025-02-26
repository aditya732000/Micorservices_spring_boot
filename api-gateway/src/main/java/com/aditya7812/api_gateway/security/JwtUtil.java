package com.aditya7812.api_gateway.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey key;

    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }

    private boolean isTokenExpired(String token) {
        return this.getAllClaimsFromToken(token).getExpiration().before(new Date());
    }

    public boolean isInvalid(String token) {
        return this.isTokenExpired(token);
    }

}




/**********************************************
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

    public String getUserIDFromToken(String token) {
        Claims claims = extractAllClaims(token);
        String userId = claims.get("userId").toString();
        return userId;
    }
}*/

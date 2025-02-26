package com.aditya7812.products_service.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
    

    public String getUserIDFromToken(String token) {
        Claims claims = extractAllClaims(token);
        String userId = claims.get("userId").toString();
        return userId;
    }
}

package com.anproject.security;

import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class JwtService {

    private static final SecretKey key =  Jwts.SIG.HS256.key().build();
    private static final long EXPIRATION_TIME = 864_000_000; 

    public String generateToken(String nickname, String role) {
        return Jwts.builder()
                .subject(nickname)
                .claim("role", role)
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getNicknameFromToken(String token) {
        Jws<Claims> claims =  Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
        return ((Claims) claims).getSubject();
    }

    public String getRoleFromToken(String token) {
        Claims claims =  (Claims) Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
        return claims.get("role", String.class);
    }
    
}

package br.com.carlospablo.todolist.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;




@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secretKey;

    public  SecretKey getSigningKey(){
        byte[] keybytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keybytes);
    }

    public String generateToken(String idUsuario){
        
        return Jwts.builder()
                .subject(idUsuario)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(getSigningKey())
                .compact();

    }

    public Boolean validateToken(String token){
        try {
            Jwts.parser()
                .verifyWith((getSigningKey()))
                .build()
                .parseSignedClaims(token);

                return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }
    // Extraindo o subject 
  public String extractSubject(String token){
    return Jwts.parser()
        .verifyWith((getSigningKey()))
        .build().parseSignedClaims(token)
        .getPayload()
        .getSubject();

  }
}

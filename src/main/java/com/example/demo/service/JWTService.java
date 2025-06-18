package com.example.demo.service;

// import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

  private String secretKey = "";

  public JWTService() {
    try {
      KeyGenerator keygen = KeyGenerator.getInstance("HMACSHA256");
      SecretKey sk = keygen.generateKey();
      secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());

    } catch (Exception e) {
      throw new RuntimeException("Error generating secret key", e);
    }
  }

  public String genrateToken(String name) {

    Map<String, Object> claims = new HashMap<>();
    claims.put("name", name);
    return Jwts.builder()
        .claims(claims)
        .subject(name)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 10)) // 10 min
        .signWith(getKey())
        .compact();
  }

  private SecretKey getKey() {
    byte[] keyByte = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyByte);
  }

  public String extractUsernameFromToken(String token) {
    return extractAllClaims(token).getSubject();
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsernameFromToken(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  private boolean isTokenExpired(String token) {
    return extractAllClaims(token).getExpiration().before(new Date());
  }

  private Claims extractAllClaims(String token) {
    return Jwts
        .parser()
        .verifyWith(getKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

}

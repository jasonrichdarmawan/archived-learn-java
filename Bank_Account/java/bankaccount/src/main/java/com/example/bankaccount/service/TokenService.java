package com.example.bankaccount.service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Calendar;

@Service
public class TokenService {
  @Value("${jwt.secret}")
  private String jwtSecret;

  public String generate(String account_number) {
    SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));

    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MINUTE, 10);

    String token = Jwts.builder()
            .claim("Account_Number", account_number)
            .setExpiration(calendar.getTime())
            .signWith(key)
            .compact();

    return token;
  }

  public boolean verify(String token) {
    SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));

    try {
      Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
      return true;
    } catch (JwtException e) {
      return false;
    }
  }

  public String refresh(String oldToken) {
    SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));

    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MINUTE, 10);

    String account_number = (String) Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(oldToken).getBody().get("Account_Number");

    String newToken = Jwts.builder()
            .claim("Account_Number", account_number)
            .setExpiration(calendar.getTime())
            .signWith(key)
            .compact();

    return newToken;
  }

  public Object getClaim(String token, String claim) {
    SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get(claim);
  }
}

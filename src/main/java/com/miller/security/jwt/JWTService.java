package com.miller.security.jwt;

import com.miller.domain.entity.ApiUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JWTService {
    @Value("${security.jwt.expiration}")
    private String expiration;

    @Value("${security.jwt.signature.key}")
    private String signatureKey;

    public String generateToken(ApiUser apiUser) {
        long expString = Long.parseLong(expiration);

        LocalDateTime dateTimeExpiration = LocalDateTime.now().plusMinutes(expString);

        Date date = Date.from(dateTimeExpiration
                .atZone(ZoneId.systemDefault())
                .toInstant());

        return Jwts
                .builder()
                .setSubject(apiUser.getUserName())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, signatureKey)
                .compact();
    }

    public Claims getClaims(String token) throws ExpiredJwtException {
        return  Jwts
                .parser()
                .setSigningKey(signatureKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = getClaims(token);

            Date expirationDate = claims.getExpiration();

            LocalDateTime date = expirationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            return !LocalDateTime.now().isAfter(date);

        } catch (Exception e) {
            return false;
        }
    }

    public String getUserName(String token) throws ExpiredJwtException {
        return getClaims(token).getSubject();
    }
}

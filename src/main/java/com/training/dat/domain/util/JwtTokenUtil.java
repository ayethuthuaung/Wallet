package com.training.dat.domain.util;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtTokenUtil {
    private static final String SECRET_KEY = "your_secret_key";

    public static String generateToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours expiration
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();
    }

    public static boolean validateToken(String token, String username) {
        return username.equals(Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject());
    }

    public static String extractUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }
}


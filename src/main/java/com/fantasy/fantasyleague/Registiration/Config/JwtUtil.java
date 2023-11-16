//package com.fantasy.fantasyleague.Registiration.Config;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import java.security.Key;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.SecretKey;
//import java.util.Calendar;
//import java.util.Date;
//
//
//@Component
//public class JwtUtil {
//
//    private final String secret=System.getenv("Secret_value");
//
//    private final long expiration= Long.parseLong(System.getenv("Expiration_Time"));
//
//
//    public String generateToken(String username) {
//
//        SecretKey key = Jwts.SIG.HS256.key().build();
//        return Jwts.builder().subject(username).signWith(key).compact();
//    }
//
////    public String getUsernameFromToken(String token) {
////        Claims claims = Jwts.parser()
////                .setSigningKey(secret)
////                .parseClaimsJws(token)
////                .getBody();
////
////        return claims.getSubject();
////    }
//
////    public boolean validateToken(String token) {
////        try {
////            Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token);
////               return true;
////        } catch (Exception e) {
////            // handle exception, e.g., log or throw a custom exception
////            System.out.println(e);
////            return false;
////        }
////    }
//}

package com.LetsWriteAndShare.lwas.auth;

import com.LetsWriteAndShare.lwas.dto.Credentials;
import com.LetsWriteAndShare.lwas.entity.User;
import com.LetsWriteAndShare.lwas.service.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;


@Service
@Primary
public class JwtTokenService implements TokenService {


    SecretKey key = Keys.hmacShaKeyFor("secret-must-be-at-least-32-chars".getBytes());

    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Token createToken(User user, Credentials credentials) {
        TokenSubject tokenSubject = new TokenSubject(user.getId(), user.isActive());
        try {
            String subject = objectMapper.writeValueAsString(tokenSubject);
            String token = Jwts.builder().setSubject(subject).signWith(key).compact();
            return new Token("Bearer",token);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public User verifyToken(String authorizationHeader) {

        try {
            if (authorizationHeader == null) return  null;
            String token = authorizationHeader.split(" ")[1];
            JwtParser parser = Jwts.parserBuilder().setSigningKey(key).build();
            Jws<Claims> claims =parser.parseClaimsJws(token);
            Long userId = Long.valueOf(claims.getBody().getSubject());
            User user = new User();
            user.setId(userId);
            return user;
        }catch (JwtException e){
            e.printStackTrace();
        }
        return  null;
    }


    public static record TokenSubject(long id, boolean active){

    }
}

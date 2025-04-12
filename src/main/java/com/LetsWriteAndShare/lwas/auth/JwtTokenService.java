package com.LetsWriteAndShare.lwas.auth;

import com.LetsWriteAndShare.lwas.dto.Credentials;
import com.LetsWriteAndShare.lwas.entity.User;
import com.LetsWriteAndShare.lwas.service.TokenService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;


@Service
@Primary
public class JwtTokenService implements TokenService {
    @Override
    public Token createToken(User user, Credentials credentials) {
        SecretKey key = Keys.hmacShaKeyFor("secret-must-be-at-least-32-chars".getBytes());
        String token = Jwts.builder().setSubject(Long.toString(user.getId())).signWith(key).compact();
        return new Token("Bearer",token);

    }

    @Override
    public User verifyToken(String authorizationHeader) {
        return null;
    }
}

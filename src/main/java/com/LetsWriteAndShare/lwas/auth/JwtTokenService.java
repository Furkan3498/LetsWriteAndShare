package com.LetsWriteAndShare.lwas.auth;

import com.LetsWriteAndShare.lwas.dto.Credentials;
import com.LetsWriteAndShare.lwas.entity.User;
import com.LetsWriteAndShare.lwas.service.TokenService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


@Service
@Primary
public class JwtTokenService implements TokenService {
    @Override
    public Token createToken(User user, Credentials credentials) {
        return null;
    }

    @Override
    public User verifyToken(String authorizationHeader) {
        return null;
    }
}

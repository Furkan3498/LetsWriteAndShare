package com.LetsWriteAndShare.lwas.auth;

import com.LetsWriteAndShare.lwas.Repository.TokenRepository;
import com.LetsWriteAndShare.lwas.dto.Credentials;
import com.LetsWriteAndShare.lwas.entity.User;
import com.LetsWriteAndShare.lwas.service.TokenService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;


@Service
@ConditionalOnProperty(name = "lwas.token-type" , havingValue = "basic")
public class OpaqueTokenService implements TokenService {

    private final TokenRepository tokenRepository;

    public OpaqueTokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Token createToken(User user, Credentials credentials) {
        return null;
    }

    @Override
    public User verifyToken(String authorizationHeader) {
        return null;
    }
}

package com.LetsWriteAndShare.lwas.auth;

import com.LetsWriteAndShare.lwas.Repository.TokenRepository;
import com.LetsWriteAndShare.lwas.dto.Credentials;
import com.LetsWriteAndShare.lwas.entity.User;
import com.LetsWriteAndShare.lwas.service.TokenService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
@ConditionalOnProperty(name = "lwas.token-type" , havingValue = "opaque")
public class OpaqueTokenService implements TokenService {

    private final TokenRepository tokenRepository;

    public OpaqueTokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Token createToken(User user, Credentials credentials) {
       String randomValue = UUID.randomUUID().toString();
       Token token = new Token();
       token.setToken(randomValue);
       token.setUser(user);
       return tokenRepository.save(token);
    }

    @Override
    public User verifyToken(String authorizationHeader) {
        if (authorizationHeader == null) return  null;

        var token = authorizationHeader.split(" ")[1];


         var tokenInDb = tokenRepository.findById(token);
         if (!tokenInDb.isPresent()) return null;
         return tokenInDb.get().getUser();
    }
}

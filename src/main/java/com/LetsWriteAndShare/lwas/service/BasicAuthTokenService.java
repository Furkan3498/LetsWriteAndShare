package com.LetsWriteAndShare.lwas.service;

import com.LetsWriteAndShare.lwas.auth.Token;
import com.LetsWriteAndShare.lwas.dto.Credentials;
import com.LetsWriteAndShare.lwas.entity.User;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class BasicAuthTokenService implements TokenService{
    @Override
    public Token createToken(User user, Credentials credentials) {
       String emailColonPassword = credentials.email() + ":" +credentials.password();
       String token = Base64.getEncoder().encodeToString(emailColonPassword.getBytes());
       return  new Token("Basic",token);
    }

    @Override
    public User verifyToken(String authorizationHeader) {
        return null;
    }
}

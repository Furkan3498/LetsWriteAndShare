package com.LetsWriteAndShare.lwas.service;

import com.LetsWriteAndShare.lwas.auth.Token;
import com.LetsWriteAndShare.lwas.dto.Credentials;
import com.LetsWriteAndShare.lwas.entity.User;

public class BasicAuthTokenService implements TokenService{
    @Override
    public Token createToken(User user, Credentials credentials) {
        return null;
    }

    @Override
    public User verifyToken(String authorizationHeader) {
        return null;
    }
}

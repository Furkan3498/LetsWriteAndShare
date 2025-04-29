package com.LetsWriteAndShare.lwas.auth;

import com.LetsWriteAndShare.lwas.dto.Credentials;
import com.LetsWriteAndShare.lwas.entity.User;
import com.LetsWriteAndShare.lwas.service.TokenService;

public class OpaqueTokenService implements TokenService {
    @Override
    public Token createToken(User user, Credentials credentials) {
        return null;
    }

    @Override
    public User verifyToken(String authorizationHeader) {
        return null;
    }
}

package com.LetsWriteAndShare.lwas.service;

import com.LetsWriteAndShare.lwas.auth.Token;
import com.LetsWriteAndShare.lwas.dto.Credentials;
import com.LetsWriteAndShare.lwas.entity.User;

public interface TokenService {

    public Token createToken(User user, Credentials credentials);


    public User verifyToken(String authorizationHeader);
}

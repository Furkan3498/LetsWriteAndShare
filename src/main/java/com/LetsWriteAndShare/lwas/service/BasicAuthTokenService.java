package com.LetsWriteAndShare.lwas.service;

import com.LetsWriteAndShare.lwas.auth.Token;
import com.LetsWriteAndShare.lwas.dto.Credentials;
import com.LetsWriteAndShare.lwas.entity.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@ConditionalOnProperty(name = "lwas.token-type" , havingValue = "basic")
//@ConditionalOnProperty(name = "lwas.token-type" , havingValue = "jwt")
public class BasicAuthTokenService implements TokenService{

    private final UserService userService;

    public BasicAuthTokenService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    private  final PasswordEncoder passwordEncoder ;


    @Override
    public Token createToken(User user, Credentials credentials) {
       String emailColonPassword = credentials.email() + ":" +credentials.password();
       String token = Base64.getEncoder().encodeToString(emailColonPassword.getBytes());
       return  new Token("Basic",token);
    }

    @Override
    public User verifyToken(String authorizationHeader) {
    if(authorizationHeader == null) return null;
        var baseEncoded64 =    authorizationHeader.split("Basic ")[1];
        var decoded = new  String(Base64.getDecoder().decode(baseEncoded64));
        var credentials = decoded.split(":");
        var email = credentials[0];
        var password = credentials[1];
        User inDb = userService.findByUser(email);
        if (!passwordEncoder.matches(password,inDb.getPassword()))return  null;
        return inDb;



    }
}

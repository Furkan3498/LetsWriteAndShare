package com.LetsWriteAndShare.lwas.service;


import com.LetsWriteAndShare.lwas.Exception.AuthenticationException;
import com.LetsWriteAndShare.lwas.auth.Token;
import com.LetsWriteAndShare.lwas.dto.AuthResponse;
import com.LetsWriteAndShare.lwas.dto.Credentials;
import com.LetsWriteAndShare.lwas.dto.UserDto;
import com.LetsWriteAndShare.lwas.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;

    //we injected TokenService. Token Service is a Interface
    //Spring will search any bean implement that TokenService
    //we used @Service Annotation on BacisAuthTokenService so that be a bean
    private final TokenService tokenService;

    private  final  PasswordEncoder passwordEncoder;

    public AuthService(UserService userService, TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }


    public AuthResponse authenticate(Credentials credentials) {

        User inDb = userService.findByUser(credentials.email());
        if (inDb == null) throw  new AuthenticationException();
        //credentials password is clear text but we hold password being hash password
        //first we must to convert password
        //we can use passwordEncoder.matches
       if (!passwordEncoder.matches(credentials.password(), inDb.getPassword())) throw new AuthenticationException();

       Token token = tokenService.createToken(inDb,credentials);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setUserDto(new UserDto(inDb));
        return authResponse;

    }
}

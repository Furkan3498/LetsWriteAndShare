package com.LetsWriteAndShare.lwas.service;


import com.LetsWriteAndShare.lwas.Exception.AuthenticationException;
import com.LetsWriteAndShare.lwas.dto.Credentials;
import com.LetsWriteAndShare.lwas.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }


    PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
    public void authenticate(Credentials credentials) {

        User inDb = userService.findByUser(credentials.email());
        if (inDb == null) throw  new AuthenticationException();
        //credentials password is clear text but we hold password being hash password
        //first we must to convert password
        //we can use passwordEncoder.matches
       if (!passwordEncoder.matches(credentials.password(), inDb.getPassword())) throw new AuthenticationException();

    }
}

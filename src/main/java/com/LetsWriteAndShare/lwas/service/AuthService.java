package com.LetsWriteAndShare.lwas.service;


import com.LetsWriteAndShare.lwas.dto.Credentials;
import com.LetsWriteAndShare.lwas.entity.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public void authenticate(Credentials credentials) {

        User inDb = userService.findByUser(credentials.email());

    }
}

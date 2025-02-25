package com.LetsWriteAndShare.lwas.auth;

import com.LetsWriteAndShare.lwas.dto.Credentials;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @PostMapping("/api/v1/auth")
    void handleAuthentication(@RequestBody Credentials credentials){

    }

}

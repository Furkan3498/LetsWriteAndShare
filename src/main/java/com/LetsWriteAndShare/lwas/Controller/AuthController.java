package com.LetsWriteAndShare.lwas.Controller;
import com.LetsWriteAndShare.lwas.dto.AuthResponse;
import com.LetsWriteAndShare.lwas.dto.Credentials;
import com.LetsWriteAndShare.lwas.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {


  private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/api/v1/auth")
    AuthResponse handleAuthentication(@Valid  @RequestBody Credentials credentials){

       return authService.authenticate(credentials);

    }



}

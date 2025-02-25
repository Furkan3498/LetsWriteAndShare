package com.LetsWriteAndShare.lwas.Controller;

import com.LetsWriteAndShare.lwas.Exception.AuthenticationException;
import com.LetsWriteAndShare.lwas.dto.Credentials;
import com.LetsWriteAndShare.lwas.errors.ApiErrors;
import com.LetsWriteAndShare.lwas.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    void handleAuthentication(@RequestBody Credentials credentials){

        authService.authenticate(credentials);

    }

    @ExceptionHandler(AuthenticationException.class)
    ResponseEntity<?>  handleAuthenticationException(AuthenticationException exception){
        ApiErrors errors = new ApiErrors();
        errors.setPath("/api/v1/auth");
        errors.setStatus(401);
        errors.setMessage(exception.getMessage());
        return ResponseEntity.status(401).body(errors);
    }

}

package com.LetsWriteAndShare.lwas.Controller;


import com.LetsWriteAndShare.lwas.Exception.NotUniqueEmailException;
import com.LetsWriteAndShare.lwas.entity.User;
import com.LetsWriteAndShare.lwas.errors.ApiErrors;
import com.LetsWriteAndShare.lwas.service.UserService;
import com.LetsWriteAndShare.lwas.utils.GenericMessage;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class UserController {


    // @CrossOrigin eğer frontendte proxt ayarlamazsak bunu kullanabiliriz


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/v1/users")
    GenericMessage createUser(@Valid @RequestBody User user) {

        userService.save(user);
        return new GenericMessage("user is created!!");
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
        //@ResponseStatus(HttpStatus.BAD_REQUEST)
        //ResponseEntity yerine böyle de kullanabilirdim.
    ResponseEntity<ApiErrors> handleMethodArgNotValidEx(MethodArgumentNotValidException exception) {
        ApiErrors apiErrors = new ApiErrors();
        apiErrors.setMessage("Validation Errors");
        apiErrors.setPath("/api/v1/users");
        apiErrors.setStatus(400);
        // Map<String, String> validationErrors = new HashMap<>();
        //for( var fieldError : exception.getBindingResult().getFieldErrors()){
        //  validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage()); }

        var validationErrors = exception.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(
                FieldError::getField, FieldError::getDefaultMessage, (existing, replacing) -> existing));
        apiErrors.setValidationErrors(validationErrors);
        return ResponseEntity.badRequest().body(apiErrors);


    }
    @ExceptionHandler(NotUniqueEmailException.class)

    ResponseEntity<ApiErrors> handleNotUniqueEmailEx(NotUniqueEmailException exception) {
        ApiErrors apiErrors = new ApiErrors();
        apiErrors.setMessage("Validation Errors");
        apiErrors.setPath("/api/v1/users");
        apiErrors.setStatus(400);

        Map<String, String> validationErrors = new HashMap<>();
        validationErrors.put("email","E-mail in use");
        apiErrors.setValidationErrors(validationErrors);
        return ResponseEntity.badRequest().body(apiErrors);


    }
}

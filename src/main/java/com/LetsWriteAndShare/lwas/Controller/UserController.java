package com.LetsWriteAndShare.lwas.Controller;


import com.LetsWriteAndShare.lwas.Exception.NotUniqueEmailException;
import com.LetsWriteAndShare.lwas.entity.User;
import com.LetsWriteAndShare.lwas.errors.ApiErrors;
import com.LetsWriteAndShare.lwas.service.UserService;
import com.LetsWriteAndShare.lwas.utils.GenericMessage;
import jakarta.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
    MessageSource messageSource;

    public UserController(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }


    @PostMapping("/api/v1/users")
    GenericMessage createUser(@Valid @RequestBody User user) {

        userService.save(user);
        String message = messageSource.getMessage("LetsWriteAndShare.create.user.success.message",null, LocaleContextHolder.getLocale());

        return new GenericMessage(message);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
        //@ResponseStatus(HttpStatus.BAD_REQUEST)
        //ResponseEntity yerine böyle de kullanabilirdim.
    ResponseEntity<ApiErrors> handleMethodArgNotValidEx(MethodArgumentNotValidException exception) {
        ApiErrors apiErrors = new ApiErrors();
        apiErrors.setPath("/api/v1/users");
        String message = messageSource.getMessage("LetsWriteAndShare.error.validation",null, LocaleContextHolder.getLocale());

        apiErrors.setMessage(message);

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

        apiErrors.setPath("/api/v1/users");
        String message = messageSource.getMessage("LetsWriteAndShare.error.validation",null, LocaleContextHolder.getLocale());
        apiErrors.setMessage(message);
        apiErrors.setStatus(400);
        String validationError = messageSource.getMessage("LetsWriteAndShare.constraint.email.notunique",null, LocaleContextHolder.getLocale());
        Map<String, String> validationErrors = new HashMap<>();
        validationErrors.put("email",validationError);
        apiErrors.setValidationErrors(validationErrors);
        return ResponseEntity.badRequest().body(apiErrors);


    }
}

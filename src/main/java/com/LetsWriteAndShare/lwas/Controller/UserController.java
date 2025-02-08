package com.LetsWriteAndShare.lwas.Controller;


import com.LetsWriteAndShare.lwas.Exception.ActivationNotificationException;
import com.LetsWriteAndShare.lwas.Exception.InvalidTokenException;
import com.LetsWriteAndShare.lwas.Exception.NotUniqueEmailException;
import com.LetsWriteAndShare.lwas.dto.UserCreate;
import com.LetsWriteAndShare.lwas.entity.User;
import com.LetsWriteAndShare.lwas.errors.ApiErrors;
import com.LetsWriteAndShare.lwas.service.UserService;
import com.LetsWriteAndShare.lwas.utils.GenericMessage;
import com.LetsWriteAndShare.lwas.utils.Messages;
import jakarta.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
    GenericMessage createUser(@Valid @RequestBody UserCreate user) {

        userService.save(user.toUser());
        String message = Messages.getMessageForLocale("LetsWriteAndShare.create.user.success.message", LocaleContextHolder.getLocale());

        return new GenericMessage(message);
    }
    @GetMapping("/api/v1/users")
    Page<User> getUsers(){
        return userService.getUsers();
    }




    @PatchMapping("/api/v1/users/{token}/active")
    GenericMessage activateUser(@PathVariable String token) {

       userService.activateUser(token);
        String message = Messages.getMessageForLocale("LetsWriteAndShare.activate.user.success.message", LocaleContextHolder.getLocale());

        return new GenericMessage(message);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
        //@ResponseStatus(HttpStatus.BAD_REQUEST)
        //ResponseEntity yerine böyle de kullanabilirdim.
    ResponseEntity<ApiErrors> handleMethodArgNotValidEx(MethodArgumentNotValidException exception) {
        ApiErrors apiErrors = new ApiErrors();
        apiErrors.setPath("/api/v1/users");
        String message = Messages.getMessageForLocale("LetsWriteAndShare.error.validation",  LocaleContextHolder.getLocale());

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

        apiErrors.setMessage(exception.getMessage());
        apiErrors.setStatus(400);

        apiErrors.setValidationErrors(exception.getValidErrors());
        return ResponseEntity.status(400).body(apiErrors);


    }
    @ExceptionHandler(ActivationNotificationException.class)

    public ResponseEntity<ApiErrors> handleActivationNotificationException(ActivationNotificationException exception) {
        ApiErrors apiErrors = new ApiErrors();

        apiErrors.setPath("/api/v1/users");

        apiErrors.setMessage(exception.getMessage());
        apiErrors.setStatus(502);

        return ResponseEntity.status(502).body(apiErrors);


    }
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ApiErrors> handleInvalidTokenException(InvalidTokenException exception) {
        ApiErrors apiErrors = new ApiErrors();
        apiErrors.setPath("/api/v1/users");
        apiErrors.setMessage(exception.getMessage());
        apiErrors.setStatus(400);

        return ResponseEntity.status(400).body(apiErrors);


    }

}

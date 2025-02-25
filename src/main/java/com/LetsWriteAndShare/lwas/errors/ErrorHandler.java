package com.LetsWriteAndShare.lwas.errors;


import com.LetsWriteAndShare.lwas.Exception.*;
import com.LetsWriteAndShare.lwas.utils.Messages;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
        //@ResponseStatus(HttpStatus.BAD_REQUEST)
        //ResponseEntity yerine böyle de kullanabilirdim.
    ResponseEntity<ApiErrors> handleMethodArgNotValidEx(MethodArgumentNotValidException exception  ,HttpServletRequest request) {
        ApiErrors apiErrors = new ApiErrors();
        apiErrors.setPath(request.getRequestURI());
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
    public ResponseEntity<ApiErrors> handleInvalidTokenException(InvalidTokenException exception, HttpServletRequest request) {
        ApiErrors apiErrors = new ApiErrors();
        apiErrors.setPath(request.getRequestURI());
        apiErrors.setMessage(exception.getMessage());
        apiErrors.setStatus(400);

        return ResponseEntity.status(400).body(apiErrors);


    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiErrors> handleEntityNotFoundException(NotFoundException exception , HttpServletRequest request) {
        ApiErrors apiErrors = new ApiErrors();
        apiErrors.setPath(request.getRequestURI());
        apiErrors.setMessage(exception.getMessage());
        apiErrors.setStatus(404);

        return ResponseEntity.status(404).body(apiErrors);


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

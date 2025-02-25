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
    @ExceptionHandler({MethodArgumentNotValidException.class,
            NotUniqueEmailException.class,
            ActivationNotificationException.class,
            InvalidTokenException.class,
            NotFoundException.class,
            AuthenticationException.class})
        //@ResponseStatus(HttpStatus.BAD_REQUEST)
        //ResponseEntity yerine böyle de kullanabilirdim.
    ResponseEntity<ApiErrors> handleException(Exception exception  ,HttpServletRequest request) {
        ApiErrors apiErrors = new ApiErrors();
        apiErrors.setPath(request.getRequestURI());
        apiErrors.setMessage(exception.getMessage());

        if (exception instanceof MethodArgumentNotValidException){
            String message = Messages.getMessageForLocale("LetsWriteAndShare.error.validation",  LocaleContextHolder.getLocale());

            apiErrors.setMessage(message);

            apiErrors.setStatus(400);
            // Map<String, String> validationErrors = new HashMap<>();
            //for( var fieldError : exception.getBindingResult().getFieldErrors()){
            //  validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage()); }

            var validationErrors = ((MethodArgumentNotValidException)exception).getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(
                    FieldError::getField, FieldError::getDefaultMessage, (existing, replacing) -> existing));
            apiErrors.setValidationErrors(validationErrors);
        } else if (exception instanceof  NotUniqueEmailException) {
            apiErrors.setStatus(400);
            apiErrors.setValidationErrors(((NotUniqueEmailException)exception).getValidErrors());
            
        } else if (exception instanceof  ActivationNotificationException) {
            apiErrors.setStatus(502);
            
        } else if (exception instanceof  InvalidTokenException) {
            apiErrors.setStatus(400);

        }else if (exception instanceof  NotFoundException) {
            apiErrors.setStatus(404);

        }else if (exception instanceof  AuthenticationException) {
            apiErrors.setStatus(401);

        }
        return ResponseEntity.status(apiErrors.getStatus()).body(apiErrors);


    }

}

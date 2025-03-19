package com.LetsWriteAndShare.lwas.errors;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DisabledException.class)
    ResponseEntity<?> handlerDisableException(DisabledException exception, HttpServletRequest request){

        ApiErrors errors= new ApiErrors();
        errors.setMessage(exception.getMessage());
        errors.setStatus(401);
        errors.setPath(request.getRequestURI());
        return ResponseEntity.status(401).body(errors);
    }
}

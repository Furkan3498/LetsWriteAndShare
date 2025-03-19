package com.LetsWriteAndShare.lwas.errors;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;


@ControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({DisabledException.class, AccessDeniedException.class})
    ResponseEntity<?> handlerDisableException(Exception exception, HttpServletRequest request){

        ApiErrors errors= new ApiErrors();
        errors.setMessage(exception.getMessage());
        errors.setPath(request.getRequestURI());

        if (exception instanceof DisabledException){
            errors.setStatus(401);
        }
        else if (exception instanceof AccessDeniedException){
            errors.setStatus(403);
        }


        return ResponseEntity.status(errors.getStatus()).body(errors);
    }
}

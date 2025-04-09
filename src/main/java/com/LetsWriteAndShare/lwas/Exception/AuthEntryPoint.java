package com.LetsWriteAndShare.lwas.Exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

public class AuthEntryPoint implements AuthenticationEntryPoint {





   // @Qualifier("handlerExceptionResolver")
    //  private HandlerExceptionResolver handlerExceptionResolver;

    //  public AuthEntryPoint(HandlerExceptionResolver handlerExceptionResolver) {
    //       this.handlerExceptionResolver = handlerExceptionResolver;
    //    }

    //   public AuthEntryPoint() {

    //   }

    // @Override
    //  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    //      handlerExceptionResolver.resolveException(request,response,null,authException);
    //   }






}

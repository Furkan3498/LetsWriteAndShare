package com.LetsWriteAndShare.lwas.configuration;

import com.LetsWriteAndShare.lwas.entity.User;
import com.LetsWriteAndShare.lwas.errors.ApiErrors;
import com.LetsWriteAndShare.lwas.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.io.OutputStream;


@Component
public class TokenFilter extends OncePerRequestFilter {

   private final TokenService tokenService;

    public TokenFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }


    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver exceptionResolver;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


      String authorizationHeader = request.getHeader("Authorization");
      if (authorizationHeader !=null){
          User user = tokenService.verifyToken(authorizationHeader);

          if (user != null){
              if (!user.isActive()){
                  exceptionResolver.resolveException(request,response,null,new DisabledException("User is disable"));
                  return;
               //   throw  new DisabledException("User is disable");
                  //    ApiErrors apiErrors = new ApiErrors();
                  //       apiErrors.setStatus(401);
                  //      apiErrors.setPath(request.getRequestURI());
                  //        apiErrors.setMessage("User is disable");
                  //       ObjectMapper  objectMapper = new ObjectMapper();

                  //       response.setStatus(401);
                  //        response.setContentType("application/json");
                  //      OutputStream outputStream = response.getOutputStream();
                  //       objectMapper.writeValue(outputStream,apiErrors);
                  //       outputStream.flush();
                  //       return;
              }
              CurrentUser currentUser = new CurrentUser(user);
              UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(currentUser,
                      null,currentUser.getAuthorities());
              authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
              SecurityContextHolder.getContext().setAuthentication(authenticationToken);
          }
      }
        filterChain.doFilter(request,response);
    }
}

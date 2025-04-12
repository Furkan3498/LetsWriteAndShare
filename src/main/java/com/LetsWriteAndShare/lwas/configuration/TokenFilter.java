package com.LetsWriteAndShare.lwas.configuration;

import com.LetsWriteAndShare.lwas.entity.User;
import com.LetsWriteAndShare.lwas.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class TokenFilter extends OncePerRequestFilter {

   private final TokenService tokenService;

    public TokenFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


      String authorizationHeader = request.getHeader("Authorization");
      if (authorizationHeader !=null){
          User user = tokenService.verifyToken(authorizationHeader);

          if (user != null){
              CurrentUser currentUser = new CurrentUser(user);
              UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(currentUser,
                      null,currentUser.getAuthorities());

          }
      }
        filterChain.doFilter(request,response);
    }
}

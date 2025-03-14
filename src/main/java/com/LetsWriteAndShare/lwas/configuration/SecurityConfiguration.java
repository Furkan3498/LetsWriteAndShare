package com.LetsWriteAndShare.lwas.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@EnableWebSecurity
@RestController
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authentication)->authentication.requestMatchers(AntPathRequestMatcher.antMatcher("/secured")).authenticated().anyRequest().permitAll());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @GetMapping("/secured")
    String securedd(){
        return "secured";
    }
    @GetMapping("/unsecured")
    String unnsecuredd(){
        return "unsecured";
    }
}

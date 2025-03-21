package com.LetsWriteAndShare.lwas.configuration;


import com.LetsWriteAndShare.lwas.Exception.AuthEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authentication)->authentication.requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.PUT,"/api/v1/users/{id}"))
                .authenticated()
                .anyRequest().permitAll());
        //http.httpBasic(Customizer.withDefaults());
        http.httpBasic(httpBasic-> httpBasic.authenticationEntryPoint(new AuthEntryPoint()));
        http.csrf(AbstractHttpConfigurer::disable); //  http.csrf(csrf->csrf.disable()); same
        http.headers(AbstractHttpConfigurer::disable); // http.headers(headers->headers.disable());

        return http.build();


    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

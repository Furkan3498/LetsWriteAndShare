package com.LetsWriteAndShare.lwas.dto;

import com.LetsWriteAndShare.lwas.entity.User;
import com.LetsWriteAndShare.lwas.validation.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreate(
        @NotBlank(message = "{LetsWriteAndShare.constraint.username.notblank}")
        @Size(min = 4, max = 255, message = "{LetsWriteAndShare.constraint.username.size}")
        String username,
        @NotBlank
        @Email
        @UniqueEmail
        String email,

        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{LetsWriteAndShare.constraint.password.pattern}")
        @Size(min = 3, max = 255, message = "{LetsWriteAndShare.constraint.password.size}")
        String password
) {

  public User toUser(){

          User user = new User();
          user.setUsername(username);
          user.setEmail(email);
          user.setPassword(password);

                return  user;





  }
}

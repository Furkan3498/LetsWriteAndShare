package com.LetsWriteAndShare.lwas.Controller;


import com.LetsWriteAndShare.lwas.entity.User;
import com.LetsWriteAndShare.lwas.errors.ApiErrors;
import com.LetsWriteAndShare.lwas.service.UserService;
import com.LetsWriteAndShare.lwas.utils.GenericMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {


   // @CrossOrigin eğer frontendte proxt ayarlamazsak bunu kullanabiliriz


    private final  UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/v1/users")
        ResponseEntity<?>  createUser(@RequestBody User user) {
    if (user.getUsername() == null || user.getUsername().isEmpty()){
        ApiErrors apiErrors = new ApiErrors();
        apiErrors.setMessage("Validation Errors");
        apiErrors.setPath("/api/v1/users");
        apiErrors.setStatus(400);
        Map<String,String> validationErrors = new HashMap<>();
        validationErrors.put("username", "username can not be null");
        apiErrors.setValidationErrors(validationErrors);



        return ResponseEntity.badRequest().body(apiErrors);
        }

        userService.save(user);
        return ResponseEntity.ok( new GenericMessage("user is created!!"));
    }


}

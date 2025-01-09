package com.LetsWriteAndShare.lwas.Controller;


import com.LetsWriteAndShare.lwas.entity.User;
import com.LetsWriteAndShare.lwas.service.UserService;
import com.LetsWriteAndShare.lwas.utils.GenericMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


   // @CrossOrigin eğer frontendte proxt ayarlamazsak bunu kullanabiliriz


    private final  UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/v1/users")
    GenericMessage createUser(@RequestBody User user) {

        userService.save(user);

        return new GenericMessage("user is created!!");
    }


}

package com.LetsWriteAndShare.lwas.Controller;

import com.LetsWriteAndShare.lwas.dto.UserCreate;
import com.LetsWriteAndShare.lwas.dto.UserDto;
import com.LetsWriteAndShare.lwas.service.UserService;
import com.LetsWriteAndShare.lwas.utils.GenericMessage;
import com.LetsWriteAndShare.lwas.utils.Messages;
import jakarta.validation.Valid;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;



@RestController
public class UserController {


    // @CrossOrigin eğer frontendte proxt ayarlamazsak bunu kullanabiliriz


    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/v1/users")
    GenericMessage createUser(@Valid @RequestBody UserCreate user) {

        userService.save(user.toUser());
        String message = Messages.getMessageForLocale("LetsWriteAndShare.create.user.success.message", LocaleContextHolder.getLocale());

        return new GenericMessage(message);
    }
    @GetMapping("/api/v1/users")
    Page<UserDto> getUsers(Pageable page){
        return userService.getUsers(page).map(UserDto::new);
    }


    @GetMapping("/api/v1/users/{id}")
    UserDto getUserById(@PathVariable long id){

        return new UserDto( userService.getUser(id));
    }



    @PatchMapping("/api/v1/users/{token}/active")
    GenericMessage activateUser(@PathVariable String token) {

       userService.activateUser(token);
        String message = Messages.getMessageForLocale("LetsWriteAndShare.activate.user.success.message", LocaleContextHolder.getLocale());

        return new GenericMessage(message);
    }




}

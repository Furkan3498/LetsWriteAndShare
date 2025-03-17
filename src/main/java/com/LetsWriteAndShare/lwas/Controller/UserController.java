package com.LetsWriteAndShare.lwas.Controller;

import com.LetsWriteAndShare.lwas.Exception.AuthenticationException;
import com.LetsWriteAndShare.lwas.configuration.CurrentUser;
import com.LetsWriteAndShare.lwas.dto.UserCreate;
import com.LetsWriteAndShare.lwas.dto.UserDto;
import com.LetsWriteAndShare.lwas.dto.UserUpdate;
import com.LetsWriteAndShare.lwas.entity.User;
import com.LetsWriteAndShare.lwas.service.TokenService;
import com.LetsWriteAndShare.lwas.service.UserService;
import com.LetsWriteAndShare.lwas.utils.GenericMessage;
import com.LetsWriteAndShare.lwas.utils.Messages;
import jakarta.validation.Valid;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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
    Page<UserDto> getUsers(Pageable page,  @AuthenticationPrincipal CurrentUser currentUser){

        return userService.getUsers(page, currentUser).map(UserDto::new);
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

    @PutMapping("/api/v1/users/{id}")
    UserDto updateUser(@PathVariable long id, @Valid @RequestBody UserUpdate userUpdate,
                       @AuthenticationPrincipal CurrentUser currentUser){



    if(currentUser.getId() != id){
        throw  new AuthenticationException();
    }
    return  new UserDto(userService.updateUser(id,userUpdate)) ;
}


}

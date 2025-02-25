package com.LetsWriteAndShare.lwas.dto;

import com.LetsWriteAndShare.lwas.auth.Token;

public class AuthResponse {

    UserDto userDto;

    Token token;

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}

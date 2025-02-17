package com.LetsWriteAndShare.lwas.dto;

import com.LetsWriteAndShare.lwas.entity.User;

public class UserDto {


    long id;
    String username;
    String email;
    String image;

    public UserDto(User user) {


        setId(user.getId());
        setUsername(user.getUsername());
        setEmail(user.getEmail());
        setImage(user.getImage());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image ;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

package com.LetsWriteAndShare.lwas.auth;

import com.LetsWriteAndShare.lwas.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
public class Token{



    @Id
    String token;

    @Transient // we dont need read database "bearer". It necesarry only client
    String prefix = "Bearer";


    //@JsonIgnore
    @ManyToOne //one user has a lot of token
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Token(String prefix, String token) {
        this.prefix = prefix;
        this.token = token;
    }

    public Token() {
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

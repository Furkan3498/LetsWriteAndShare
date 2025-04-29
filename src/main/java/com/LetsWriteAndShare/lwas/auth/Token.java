package com.LetsWriteAndShare.lwas.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Token{

    String prefix = "Bearer";

    @Id
    String token;

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

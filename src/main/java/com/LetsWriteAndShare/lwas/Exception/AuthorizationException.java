package com.LetsWriteAndShare.lwas.Exception;

public class AuthorizationException extends RuntimeException{
    public AuthorizationException(){
        super("Forbidden");
    }
}

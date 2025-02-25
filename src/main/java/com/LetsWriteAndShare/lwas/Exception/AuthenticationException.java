package com.LetsWriteAndShare.lwas.Exception;

import com.LetsWriteAndShare.lwas.utils.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

public class AuthenticationException extends RuntimeException{

    public AuthenticationException(){
        super(Messages.getMessageForLocale("LetsWriteAndShare.auth.invalid.credentials", LocaleContextHolder.getLocale()));
    }
}

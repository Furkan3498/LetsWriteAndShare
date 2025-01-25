package com.LetsWriteAndShare.lwas.Exception;

import com.LetsWriteAndShare.lwas.utils.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

public class InvalidTokenException extends RuntimeException{

    public  InvalidTokenException(){
        super(Messages.getMessageForLocale("LetsWriteAndShare.activate.user.invalid.token", LocaleContextHolder.getLocale()));
    }
}

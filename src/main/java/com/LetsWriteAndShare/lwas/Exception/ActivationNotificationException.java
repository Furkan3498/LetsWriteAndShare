package com.LetsWriteAndShare.lwas.Exception;

import com.LetsWriteAndShare.lwas.utils.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

public class ActivationNotificationException extends RuntimeException{

    public ActivationNotificationException(){
        super(Messages.getMessageForLocale("LetsWriteAndShare.create.user.email.failure", LocaleContextHolder.getLocale()));
    }
}

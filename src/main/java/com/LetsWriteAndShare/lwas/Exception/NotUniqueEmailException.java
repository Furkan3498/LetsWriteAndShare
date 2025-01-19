package com.LetsWriteAndShare.lwas.Exception;

import com.LetsWriteAndShare.lwas.utils.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Collections;
import java.util.Map;

public class NotUniqueEmailException extends RuntimeException{

    public NotUniqueEmailException(){

        super(Messages.getMessageForLocale("LetsWriteAndShare.error.validation",  LocaleContextHolder.getLocale()));
    }

    public Map<String,String> getValidErrors(){
        return Collections.singletonMap("email",Messages.getMessageForLocale("LetsWriteAndShare.constraint.email.notunique",  LocaleContextHolder.getLocale()));
    }
}

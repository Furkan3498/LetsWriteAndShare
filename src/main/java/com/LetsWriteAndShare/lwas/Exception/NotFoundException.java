package com.LetsWriteAndShare.lwas.Exception;

import com.LetsWriteAndShare.lwas.utils.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

public class NotFoundException extends RuntimeException{

    public NotFoundException(long id){

        super(Messages.getMessageForLocale("LetsWriteAndShare.user.not.found", LocaleContextHolder.getLocale(),id));

    }
}

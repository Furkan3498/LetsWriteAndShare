package com.LetsWriteAndShare.lwas.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


//Parametreleri sırasıyla;
//çalışacak class
//Nerelerde kullanılacağı
//Ne zaman çalışacağı
@Constraint(validatedBy =  UniqueEmailValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {


    String message() default "{LetsWriteAndShare.constraint.email.notunique}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

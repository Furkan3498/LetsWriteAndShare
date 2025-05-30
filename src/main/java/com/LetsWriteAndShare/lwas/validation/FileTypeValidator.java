package com.LetsWriteAndShare.lwas.validation;

import com.LetsWriteAndShare.lwas.file.FileService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import java.util.Arrays;
import java.util.stream.Collectors;

public class FileTypeValidator implements ConstraintValidator<FileType, String> {


    private final  FileService fileService;
    String[] types;

    public FileTypeValidator(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public void initialize(FileType constraintAnnotation) {
        this.types = constraintAnnotation.types();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value ==null || value.isEmpty())return  true;

        String type = fileService.detectedType(value);
        for (String validType : types){
            if (type.contains(validType)) return true;
        }

        String validTypes = Arrays.stream(types).collect(Collectors.joining(","));
        context.disableDefaultConstraintViolation();
        HibernateConstraintValidatorContext hibernateConstraintValidatorContext = context.unwrap(HibernateConstraintValidatorContext.class);
        hibernateConstraintValidatorContext.addMessageParameter("types",validTypes);
        hibernateConstraintValidatorContext.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()).addConstraintViolation();
        return false;

    }
}

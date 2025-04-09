package com.LetsWriteAndShare.lwas.validation;

import com.LetsWriteAndShare.lwas.file.FileService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileTypeValidator implements ConstraintValidator<FileType, String> {


    private final  FileService fileService;

    public FileTypeValidator(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        String type = fileService.detectedType(value);
        return false;
    }
}

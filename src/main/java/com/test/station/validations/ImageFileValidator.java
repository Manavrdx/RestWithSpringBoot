package com.test.station.validations;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ImageFileValidator implements ConstraintValidator<ValidImage, MultipartFile> {

    @Override
    public void initialize(ValidImage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MultipartFile part, ConstraintValidatorContext context) {
        if (part == null) {
            return true;
        }
        String contentType = part.getContentType();
        if (contentType != null) {
            return isSupportedContentType(contentType);
        } else {
            return false;
        }
    }

    private boolean isSupportedContentType(String contentType) {
        return contentType.contains("image/");
    }
}
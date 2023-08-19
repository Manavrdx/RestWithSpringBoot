package com.test.station.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* Custom validator to validate whether the given input is in image format or not */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ImageFileValidator.class})
public @interface ValidImage {
    String message() default "Provided File is not Image, Required File Type is Image";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
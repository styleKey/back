package com.thekey.stylekeyserver.common.annotation;

import com.thekey.stylekeyserver.common.validate.ListSizeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ListSizeValidator.class)
public @interface ExactSize {

    String message() default "Size must be exactly {size}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int size();
}

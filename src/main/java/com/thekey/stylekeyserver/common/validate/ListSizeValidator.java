package com.thekey.stylekeyserver.common.validate;

import com.thekey.stylekeyserver.common.annotation.ExactSize;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;

public class ListSizeValidator implements ConstraintValidator<ExactSize, List<?>> {

    private int size;

    @Override
    public void initialize(ExactSize constraintAnnotation) {
        this.size = constraintAnnotation.size();
    }

    @Override
    public boolean isValid(List<?> value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value.size() == size;
    }
}

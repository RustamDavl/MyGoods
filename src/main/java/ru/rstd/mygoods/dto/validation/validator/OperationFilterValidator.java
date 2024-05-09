package ru.rstd.mygoods.dto.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.rstd.mygoods.dto.validation.annotation.OperationFilter;

public class OperationFilterValidator implements ConstraintValidator<OperationFilter, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null)
            return true;
        return value.equals(">") || value.equals("<");
    }
}

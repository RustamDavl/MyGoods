package ru.rstd.mygoods.dto.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.rstd.mygoods.dto.validation.annotation.NameFilter;
import ru.rstd.mygoods.dto.validation.annotation.OperationFilter;

public class NameFilterValidator implements ConstraintValidator<NameFilter, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null)
            return true;
        return value.matches("[a-zA-Zа-яА-Я]+");
    }
}

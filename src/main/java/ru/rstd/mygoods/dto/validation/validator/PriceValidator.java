package ru.rstd.mygoods.dto.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.rstd.mygoods.dto.validation.annotation.ValidPrice;


public class PriceValidator implements ConstraintValidator<ValidPrice, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null)
            return true;
        double val = Double.parseDouble(value);
        return val >= (double) 0;
    }
}

package ru.rstd.mygoods.dto.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.rstd.mygoods.dto.validation.annotation.InStock;

public class InStockValidator implements ConstraintValidator<InStock, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.equalsIgnoreCase("null") ||
               value.equalsIgnoreCase("false") ||
               value.equalsIgnoreCase("true");
    }
}

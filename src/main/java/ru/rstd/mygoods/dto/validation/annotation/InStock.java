package ru.rstd.mygoods.dto.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.rstd.mygoods.dto.validation.validator.InStockValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = InStockValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InStock {

    String message() default "{inStock field can be null, false or true.}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

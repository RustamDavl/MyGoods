package ru.rstd.mygoods.dto.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.rstd.mygoods.dto.validation.validator.PriceValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PriceValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ValidPrice {

    String message() default "{Price can not be less than 0.}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

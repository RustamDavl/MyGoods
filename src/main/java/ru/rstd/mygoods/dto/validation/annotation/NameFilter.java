package ru.rstd.mygoods.dto.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.rstd.mygoods.dto.validation.validator.NameFilterValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NameFilterValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NameFilter {

    String message() default "{Name can not contain anything but characters.}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

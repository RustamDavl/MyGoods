package ru.rstd.mygoods.dto.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.rstd.mygoods.dto.validation.validator.OperationFilterValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = OperationFilterValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OperationFilter {

    String message() default "{Operation can be only > or <.}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

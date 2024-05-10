package ru.rstd.mygoods.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.rstd.mygoods.exception.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(GoodsNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionBody handleResourceNotFoundException(GoodsNotFoundException e) {
        return new ExceptionBody(e.getMessage());
    }

    @ExceptionHandler({DeliveryDocumentNotFoundException.class, SaleDocumentNotFoundException.class})
    @ResponseStatus(NOT_FOUND)
    public ExceptionBody handleDeliveryDocumentNotFoundException(RuntimeException e) {
        return new ExceptionBody(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionBody handleIllegalArgumentException(IllegalArgumentException e) {
        return new ExceptionBody(e.getMessage());
    }

    @ExceptionHandler(DocumentDuplicateException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionBody handleDocumentDuplicateException(DocumentDuplicateException e) {
        return new ExceptionBody(e.getMessage());
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionBody handleNumberFormatException(NumberFormatException e) {
        return new ExceptionBody(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionBody handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ExceptionBody exceptionBody = new ExceptionBody("validation failed");
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        exceptionBody.setErrors(
                fieldErrors.stream()
                        .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage))
        );
        return exceptionBody;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionBody handleConstraintViolationException(ConstraintViolationException e) {
        ExceptionBody exceptionBody = new ExceptionBody("validation failed");
        exceptionBody.setErrors(e.getConstraintViolations().stream()
                .collect(Collectors.toMap(constraintViolation -> constraintViolation.getPropertyPath().toString(),
                        ConstraintViolation::getMessage)));
        return exceptionBody;
    }
}

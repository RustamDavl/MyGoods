package ru.rstd.mygoods.exception;

public class DeliveryDocumentNotFoundException extends RuntimeException {
    public DeliveryDocumentNotFoundException(String message) {
        super(message);
    }
}

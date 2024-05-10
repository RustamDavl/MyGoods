package ru.rstd.mygoods.exception;

public class SaleDocumentNotFoundException extends RuntimeException {
    public SaleDocumentNotFoundException(String message) {
        super(message);
    }
}

package com.lucas.sistemabancario.exception;

public class SaldoIsNotEnoughException extends RuntimeException {
    public SaldoIsNotEnoughException(String message) {
        super(message);
    }
}

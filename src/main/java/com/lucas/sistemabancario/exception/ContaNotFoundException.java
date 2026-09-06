package com.lucas.sistemabancario.exception;

public class ContaNotFoundException extends RuntimeException {
    public ContaNotFoundException(String message) {
        super(message);
    }
}

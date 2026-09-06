package com.lucas.sistemabancario.exception;

public class ContaAlreadyExistsException extends RuntimeException {
    public ContaAlreadyExistsException(String message) {
        super(message);
    }
}

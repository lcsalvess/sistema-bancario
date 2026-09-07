package com.lucas.sistemabancario.exception;

public class ContaIsNotActiveException extends RuntimeException {
    public ContaIsNotActiveException(String message) {
        super(message);
    }
}

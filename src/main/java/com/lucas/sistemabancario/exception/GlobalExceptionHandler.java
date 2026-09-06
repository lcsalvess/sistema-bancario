package com.lucas.sistemabancario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ClienteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String tratarClientNotFound(ClienteNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(ContaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String tratarContaNotFound(ContaNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(ContaAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String tratarContaAlreadyExists(ContaAlreadyExistsException exception) {
        return exception.getMessage();
    }
}

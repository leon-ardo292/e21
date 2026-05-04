package org.e2e.labe2e01.shared.application;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            NoSuchElementException.class,
            EmptyResultDataAccessException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFound() {
    }
}

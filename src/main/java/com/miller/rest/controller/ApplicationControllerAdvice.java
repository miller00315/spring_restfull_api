package com.miller.rest.controller;

import com.miller.exception.BusinessLogicException;
import com.miller.exception.SolicitationNotFoundException;
import com.miller.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
@RestControllerAdvice // Build the body
public class ApplicationControllerAdvice {

    @ExceptionHandler(BusinessLogicException.class) // Handle the exceptions
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleBusinessLogicException(BusinessLogicException e) {
        String message = e.getMessage();
        return new ApiErrors(message);
    }

    @ExceptionHandler(SolicitationNotFoundException.class) // Handle the exceptions
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleSolicitationNotFoundException(SolicitationNotFoundException e) {

        return new ApiErrors(e.getMessage());
    }
}

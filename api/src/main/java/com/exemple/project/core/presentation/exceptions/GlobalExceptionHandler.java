package com.exemple.project.core.presentation.exceptions;

import com.exemple.project.core.exceptions.ConflictException;
import com.exemple.project.core.exceptions.IllegalParameterException;
import com.exemple.project.core.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handleNotFound(ResourceNotFoundException e) {
        return new ExceptionDto(404, e.getMessage());
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ExceptionDto handleConflictException(ConflictException e) {
        return new ExceptionDto(409, e.getMessage());
    }

    @ExceptionHandler(IllegalParameterException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_CONTENT)
    public ExceptionDto handleIllegalParameter(IllegalParameterException e) {
        return new ExceptionDto(422, e.getMessage());
    }
}
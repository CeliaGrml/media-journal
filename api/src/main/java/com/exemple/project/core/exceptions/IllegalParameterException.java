package com.exemple.project.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_CONTENT)
public class IllegalParameterException extends RuntimeException {
    public IllegalParameterException(String message) {
        super(message);
    }
}

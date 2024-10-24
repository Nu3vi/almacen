package com.cubas.almacen.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidateServiceException extends RuntimeException {


    private static final long serialVersionUID = 1L;
    public ValidateServiceException() {
    }

    public ValidateServiceException(String message) {
        super(message);
    }

    public ValidateServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidateServiceException(Throwable cause) {
        super(cause);
    }

    public ValidateServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

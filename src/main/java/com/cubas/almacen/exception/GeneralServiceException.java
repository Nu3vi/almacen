package com.cubas.almacen.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.INTERNAL_SERVER_ERROR)
public class GeneralServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public GeneralServiceException() {
    }

    public GeneralServiceException(String message) {
        super(message);
    }

    public GeneralServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneralServiceException(Throwable cause) {
        super(cause);
    }

    public GeneralServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

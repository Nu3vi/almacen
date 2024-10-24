package com.cubas.almacen.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoDataFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    public NoDataFoundException() {
    }

    public NoDataFoundException(String message) {
        super(message);
    }

    public NoDataFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoDataFoundException(Throwable cause) {
        super(cause);
    }

    public NoDataFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

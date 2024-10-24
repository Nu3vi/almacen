package com.cubas.almacen.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WrapperResponse<T> {
    private Boolean ok;
    private String message;
    private T body;

    public ResponseEntity<WrapperResponse<T>> createResponse() {
        return new ResponseEntity<>(this, HttpStatus.OK);
    }

    public ResponseEntity<WrapperResponse<T>> createResponse(HttpStatus status) {
        return new ResponseEntity<>(this, status);
    }
}

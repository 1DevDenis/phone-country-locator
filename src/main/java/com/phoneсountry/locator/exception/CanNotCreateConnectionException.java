package com.phoneсountry.locator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CanNotCreateConnectionException extends RuntimeException {
    public CanNotCreateConnectionException(String errorMessage) {
        super(errorMessage);
    }
}

package com.phonecountry.locator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RowsNotFoundException extends RuntimeException {
    public RowsNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}

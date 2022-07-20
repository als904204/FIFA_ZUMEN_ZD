package com.toy.fifa.Controller.ExceptionConfig;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity not found")
public class DataNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * @param message
     * @return 404 ERROR PAGE
     * **/
    public DataNotFoundException(String message) {
        super(message);
    }

}
package com.ontime.crrs.business.table.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TableUnavailableAdvice {

    @ResponseBody
    @ExceptionHandler(com.ontime.crrs.business.table.exception.TableUnavailableException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String restaurantNotFoundHandler(TableUnavailableException e) {
        return e.getMessage();
    }

}
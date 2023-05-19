package com.ontime.crrs.business.table.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TableNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(TableNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String tableNotFoundHandler(TableNotFoundException e) {
        return e.getMessage();
    }

}
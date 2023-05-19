package com.ontime.crrs.business.workinghours.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NonWorkingHoursAdvice {

    @ResponseBody
    @ExceptionHandler(NonWorkingHoursException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String nonWorkingHoursHandler(NonWorkingHoursException e) {
        return e.getMessage();
    }

}
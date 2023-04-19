package com.ontime.crrs.business.workinghours.exception;

public class WorkingHoursNotFoundException extends RuntimeException {

    public WorkingHoursNotFoundException(String message) {
        super(message);
    }
}
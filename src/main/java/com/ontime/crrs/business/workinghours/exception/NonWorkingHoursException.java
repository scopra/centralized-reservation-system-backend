package com.ontime.crrs.business.workinghours.exception;

public class NonWorkingHoursException extends RuntimeException {

    public NonWorkingHoursException() {
        super("Reservation cannot be made during non-working hours.");
    }
}
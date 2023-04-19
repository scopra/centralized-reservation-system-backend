package com.ontime.crrs.business.reservation.exeption;

import java.util.UUID;

public class ReservationNotFoundException extends RuntimeException {

    public ReservationNotFoundException(String message) {
        super(message);
    }

    public ReservationNotFoundException(UUID reservationId) {
        super("Could not find reservation with ID: " + reservationId);
    }

}
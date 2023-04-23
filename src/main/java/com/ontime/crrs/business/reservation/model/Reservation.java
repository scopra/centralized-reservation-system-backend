package com.ontime.crrs.business.reservation.model;

import com.ontime.crrs.business.restaurant.model.Restaurant;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
@Component
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Reservation implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID reservationId;
    private LocalDate date;
    private LocalTime time;
    private int numberOfGuests;
    private String description;
    private UUID userId;
    private Restaurant restaurant;

}
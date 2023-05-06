package com.ontime.crrs.business.reservation.model;

import com.ontime.crrs.business.restaurant.model.Restaurant;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@Component
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Reservation implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int capacity;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String specialComment;
    private Restaurant restaurant;

}
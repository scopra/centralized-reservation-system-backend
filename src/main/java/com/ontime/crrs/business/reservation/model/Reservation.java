package com.ontime.crrs.business.reservation.model;

import com.ontime.crrs.business.location.model.Location;
import lombok.*;
import org.springframework.cglib.core.Local;
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

    private LocalDate date;
    private LocalTime time;
    private int numberOfGuests;
    private String description;


}
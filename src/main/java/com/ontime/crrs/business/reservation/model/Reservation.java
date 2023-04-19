package com.ontime.crrs.business.reservation.model;

import com.ontime.crrs.business.location.model.Location;
import com.ontime.crrs.business.restaurant.model.Restaurant;
import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import lombok.*;
import org.springframework.cglib.core.Local;
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

    private LocalDate date;
    private LocalTime time;
    private int numberOfGuests;
    private String description;
    private UUID userId;
    private Restaurant restaurant;



}
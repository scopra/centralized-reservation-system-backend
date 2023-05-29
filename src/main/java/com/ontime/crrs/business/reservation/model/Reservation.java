package com.ontime.crrs.business.reservation.model;

import com.ontime.crrs.business.menuitem.model.MenuItem;
import com.ontime.crrs.business.restaurant.model.Restaurant;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@Component
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Reservation implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int numberOfGuests;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String specialComment;
    private Restaurant restaurant;
    private List<MenuItem> menuItems;

    public static DayOfWeek getReservationDay(LocalDate date) {
        return date.getDayOfWeek();
    }

}
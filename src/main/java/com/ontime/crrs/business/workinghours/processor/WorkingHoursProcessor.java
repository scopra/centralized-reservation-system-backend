package com.ontime.crrs.business.workinghours.processor;

import com.ontime.crrs.business.reservation.model.Reservation;
import com.ontime.crrs.business.restaurant.model.Restaurant;
import org.springframework.stereotype.Service;

@Service
public interface WorkingHoursProcessor {

    boolean isDuringWorkingHours(Restaurant restaurant, Reservation reservation);

}
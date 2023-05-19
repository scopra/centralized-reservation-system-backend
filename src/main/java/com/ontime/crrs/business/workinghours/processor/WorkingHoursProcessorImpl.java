package com.ontime.crrs.business.workinghours.processor;

import com.ontime.crrs.business.reservation.model.Reservation;
import com.ontime.crrs.business.workinghours.exception.NonWorkingHoursException;
import com.ontime.crrs.persistence.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkingHoursProcessorImpl implements WorkingHoursProcessor {

    private final RestaurantService restaurantService;

    public void isDuringWorkingHours(Reservation reservation) {
        if (!resolveTime(reservation)) {
            throw new NonWorkingHoursException();
        }
    }

    private boolean resolveTime(Reservation reservation) {
        var workingHours = restaurantService.findRestaurantByName(reservation.getRestaurant().getName()).getWorkingHours();
        var reserveFrom = reservation.getStartTime();
        var reserveTo = reservation.getEndTime();
        var worksFrom = workingHours.getOpenTime();
        var worksTo = workingHours.getCloseTime();

        return (reserveFrom.isAfter(worksFrom) || reserveFrom.equals(worksFrom)) &&
                (reserveTo.isBefore(worksTo) || reserveTo.equals(worksTo));
    }

}
package com.ontime.crrs.business.workinghours.processor;

import com.ontime.crrs.business.reservation.model.Reservation;
import com.ontime.crrs.business.restaurant.model.Restaurant;
import com.ontime.crrs.business.workinghours.exception.NonWorkingHoursException;
import com.ontime.crrs.business.workinghours.model.WorkingHours;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkingHoursProcessorImpl implements WorkingHoursProcessor {

    public boolean isDuringWorkingHours(Reservation reservation) {
        var restaurant = reservation.getRestaurant();
        var openTime = restaurant.getWorkingHours().getOpenTime();
        var closeTime = restaurant.getWorkingHours().getCloseTime();

        if (!resolveTime(reservation)) {
            throw new NonWorkingHoursException();
        }

        return true;
    }

    private boolean resolveTime(Reservation reservation) {
        var restaurantWorkingHours = reservation.getRestaurant().getWorkingHours();
        var reserveFrom = reservation.getStartTime();
        var reserveTo = reservation.getEndTime();
        var worksFrom = restaurantWorkingHours.getOpenTime();
        var worksTo = restaurantWorkingHours.getCloseTime();

        return (reserveFrom.isAfter(worksFrom) || reserveFrom.equals(worksFrom)) &&
                (reserveTo.isBefore(worksTo) || reserveTo.equals(worksTo));
    }

}
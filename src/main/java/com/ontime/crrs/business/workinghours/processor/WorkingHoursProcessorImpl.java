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

    public boolean isDuringWorkingHours(Restaurant restaurant, Reservation reservation) {
        var openTime = restaurant.getWorkingHours().getOpenTime();
        var closeTime = restaurant.getWorkingHours().getCloseTime();

        if (!resolveTime(reservation, restaurant.getWorkingHours())) {
            throw new NonWorkingHoursException();
        }

        return true;
    }

    private boolean resolveTime(Reservation reservation, WorkingHours workingHours) {
        var worksFrom = workingHours.getOpenTime();
        var worksTo = workingHours.getCloseTime();
        var reserveFrom = reservation.getStartTime();
        var reserveTo = reservation.getEndTime();

        return (reserveFrom.isAfter(worksFrom) || reserveFrom.equals(worksFrom))
                && (reserveTo.isBefore(worksTo) || reserveTo.equals(worksTo));
    }

}
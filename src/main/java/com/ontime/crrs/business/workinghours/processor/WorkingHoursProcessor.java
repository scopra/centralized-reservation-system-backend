package com.ontime.crrs.business.workinghours.processor;

import com.ontime.crrs.business.workinghours.exception.NonWorkingHoursException;
import com.ontime.crrs.persistence.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class WorkingHoursProcessor {

    private final RestaurantService restaurantService;

    public boolean isDuringWorkingHours(String restaurantName, LocalTime now) {
        var restaurant = restaurantService.findRestaurantByName(restaurantName);
        var openTime = restaurant.getWorkingHours().getOpenTime().toLocalTime();
        var closeTime = restaurant.getWorkingHours().getCloseTime().toLocalTime();

        if(now.isAfter(openTime.minusSeconds(1)) && now.isBefore(closeTime.minusSeconds(1))){
            return true;
        }

        throw new NonWorkingHoursException();
    }

}
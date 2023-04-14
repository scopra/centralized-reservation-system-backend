package com.ontime.crrs.business.restaurant.processor;

import com.ontime.crrs.business.restaurant.model.Restaurant;
import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import com.ontime.crrs.persistence.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static org.springframework.beans.BeanUtils.copyProperties;

@Component
@RequiredArgsConstructor
public class RestaurantProcessor {

    private final RestaurantService restaurantService;

    public RestaurantEntity transferProperties(Restaurant restaurantModel, String name) {
        var updatedRestaurantEntity = restaurantService.findRestaurantByName(name);

        copyProperties(restaurantModel, updatedRestaurantEntity);
        copyProperties(restaurantModel.getLocation(), updatedRestaurantEntity.getLocation());

        restaurantService.updateRestaurant(updatedRestaurantEntity);

        return updatedRestaurantEntity;
    }

}
package com.ontime.crrs.business.mapper.restaurant;

import com.ontime.crrs.business.mapper.MappingProcessor;
import com.ontime.crrs.business.restaurant.model.Restaurant;
import com.ontime.crrs.persistence.location.entity.LocationEntity;
import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import org.springframework.stereotype.Component;

import static org.springframework.beans.BeanUtils.copyProperties;

@Component
public class RestaurantMapper extends MappingProcessor<RestaurantEntity, Restaurant> {

    @Override
    public RestaurantEntity mapModelToEntity(Restaurant restaurantModel) {
        var restaurantEntity = createEntityInstance();

        copyProperties(restaurantModel, restaurantEntity, "id");

        if (restaurantModel.getLocation() != null) {
            var locationEntity = new LocationEntity();
            copyProperties(restaurantModel.getLocation(), locationEntity);
            restaurantEntity.setLocation(locationEntity);
        }

        return restaurantEntity;
    }

    @Override
    protected RestaurantEntity createEntityInstance() {
        return new RestaurantEntity();
    }

    @Override
    protected Restaurant createModelInstance() {
        return new Restaurant();
    }
}
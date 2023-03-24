package com.ontime.crrs.persistence.restaurant.helper;

import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import com.ontime.crrs.persistence.restaurant.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;

@Component
@RequiredArgsConstructor
public class RestaurantHelper {

    public RestaurantEntity mapRestaurantModelToEntity(Restaurant restaurantModel) {
        var restaurantEntity = new RestaurantEntity();

        copyProperties(restaurantModel, restaurantEntity);

        return restaurantEntity;
    }

    public Restaurant mapRestaurantEntityToModel(RestaurantEntity restaurantEntity) {
        var restaurantModel = new Restaurant();

        copyProperties(restaurantEntity, restaurantModel, "id");

        return restaurantModel;
    }

    public List<Restaurant> mapListOfEntitiesToListOfModels(List<RestaurantEntity> restaurantEntityList) {
        return restaurantEntityList.stream()
                .map(this::mapRestaurantEntityToModel)
                .collect(Collectors.toList());
    }

    public List<RestaurantEntity> mapListOfModelsToListOfEntities(List<Restaurant> restaurantModelList) {
        return restaurantModelList.stream()
                .map(this::mapRestaurantModelToEntity)
                .collect(Collectors.toList());
    }

}
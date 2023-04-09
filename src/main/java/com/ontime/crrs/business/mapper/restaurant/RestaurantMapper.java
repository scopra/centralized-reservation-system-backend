package com.ontime.crrs.business.mapper.restaurant;

import com.ontime.crrs.business.mapper.EntityModelMapper;
import com.ontime.crrs.business.restaurant.model.Restaurant;
import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;

public class RestaurantMapper implements EntityModelMapper<RestaurantEntity, Restaurant> {

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
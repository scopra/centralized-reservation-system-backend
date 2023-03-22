package com.ontime.crrs.persistence.restaurant.helper;

import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import com.ontime.crrs.persistence.restaurant.model.Restaurant;
import com.ontime.crrs.persistence.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;

@Component
@RequiredArgsConstructor
public class RestaurantHelper {

    private final RestaurantService restaurantService;

    public RestaurantEntity mapRestaurantModelToEntity(Restaurant restaurantModel) {

        var restaurantEntity = new RestaurantEntity();

        copyProperties(restaurantModel, restaurantEntity);
        //restaurantEntity.setId(restaurantService.findRestaurantIdByName(restaurantModel.getName()));
        //ovdje je problem kada mapiramo novi restoran, koji nema u bazi, onda bi bio belaj

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





        /*return restaurantEntity.builder()
                .id(restaurantService.findRestaurantIdByName(restaurantModel.getName()))
                .name(restaurantModel.getName())
                .description(restaurantModel.getDescription())
                .capacity(restaurantModel.getCapacity())
                .phoneNumber(restaurantModel.getPhoneNumber())
                .build();*/


      /*return restaurantModel.builder()
                .name(restaurantEntity.getName())
                .description(restaurantEntity.getDescription())
                .capacity(restaurantEntity.getCapacity())
                .phoneNumber(restaurantEntity.getPhoneNumber())
                .build();*/

}
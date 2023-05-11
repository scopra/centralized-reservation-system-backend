package com.ontime.crrs.business.mapper.restaurant;

import com.ontime.crrs.business.mapper.location.LocationMapper;
import com.ontime.crrs.business.mapper.user.UserMapper;
import com.ontime.crrs.business.restaurant.model.Restaurant;
import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {LocationMapper.class,
        UserMapper.class},
        componentModel = "spring")
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    Restaurant entityToModel(RestaurantEntity entity);

    @Mapping(target = "id", ignore = true)
    RestaurantEntity modelToEntity(Restaurant model);

    List<Restaurant> entitiesToModels(List<RestaurantEntity> entities);

    List<RestaurantEntity> modelsToEntities(List<Restaurant> models);

}
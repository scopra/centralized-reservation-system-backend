package com.ontime.crrs.persistence.restaurant.service;

import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface RestaurantService {

    RestaurantEntity updateRestaurant(RestaurantEntity restaurant);

    RestaurantEntity findRestaurantById(UUID id);

    UUID findRestaurantIdByName(String name);

    RestaurantEntity findRestaurantByName(String name);

    List<RestaurantEntity> findAllRestaurants();

    List<RestaurantEntity> findAllRestaurantsInMunicipality(String municipality);

    List<RestaurantEntity> findAllRestaurantsInCity(String city);

    RestaurantEntity findRestaurantByAddress(String address);

    boolean checkIfRestaurantExistsById(UUID id);

    void deleteRestaurantById(UUID id);

    void deleteAllRestaurants();

}
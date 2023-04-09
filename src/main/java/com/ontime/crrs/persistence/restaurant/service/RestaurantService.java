package com.ontime.crrs.persistence.restaurant.service;

import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RestaurantService {

    RestaurantEntity updateRestaurant(RestaurantEntity restaurant);

    Optional<RestaurantEntity> findRestaurantById(UUID id);

    UUID findRestaurantIdByName(String name);

    Optional<RestaurantEntity> findRestaurantByName(String name);

    List<RestaurantEntity> findAllRestaurants();

    List<RestaurantEntity> findAllRestaurantsInMunicipality(String municipality);

    List<RestaurantEntity> findAllRestaurantsInCity(String city);

    Optional<RestaurantEntity> findRestaurantByAddress(String address);

    boolean checkIfRestaurantExists(UUID id);

    void deleteRestaurantById(UUID id);

    void deleteAllRestaurants();
}
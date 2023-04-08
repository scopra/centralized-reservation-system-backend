package com.ontime.crrs.persistence.restaurant.service;

import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import com.ontime.crrs.persistence.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class  RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantEntity updateRestaurant(RestaurantEntity restaurantEntity) {
        return repository.save(restaurantEntity);
    }

    public Optional<RestaurantEntity> findRestaurantById(UUID id) {
        return repository.findById(id);
    }

    public UUID findRestaurantIdByName(String name) {
        return repository.findRestaurantIdByName(name);
    }

    public Optional<RestaurantEntity> findRestaurantByName(String name) {
        return repository.findRestaurantByName(name);
    }

    public List<RestaurantEntity> findAllRestaurants() {
        return repository.findAll();
    }

    public boolean checkIfRestaurantExists(UUID id) {
        return repository.existsById(id);
    }

    public void deleteRestaurantById(UUID id) {
        repository.deleteById(id);
    }

    public void deleteAllRestaurants() {
        repository.deleteAll();
    }

}
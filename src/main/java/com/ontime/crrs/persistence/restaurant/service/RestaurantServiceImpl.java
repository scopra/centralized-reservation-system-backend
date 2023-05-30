package com.ontime.crrs.persistence.restaurant.service;

import com.ontime.crrs.business.restaurant.exception.RestaurantNotFoundException;
import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import com.ontime.crrs.persistence.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantEntity updateRestaurant(RestaurantEntity restaurant) {
        return repository.save(restaurant);
    }

    public RestaurantEntity findRestaurantById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    public UUID findRestaurantIdByName(String name) {
        var id = repository.findRestaurantIdByName(name);

        if (id == null) {
            throw new RestaurantNotFoundException("Could not find restaurant with name " + name);
        }

        return id;
    }

    public RestaurantEntity findRestaurantByName(String name) {
        return repository.findRestaurantByName(name)
                .orElseThrow(() -> new RestaurantNotFoundException("Could not find restaurant with name " +
                        name));
    }

    public List<RestaurantEntity> findAllRestaurants() {
        return repository.findAll();
    }

    public List<RestaurantEntity> findAllRestaurantsInMunicipality(String municipality) {
        return repository.findRestaurantsByLocation_Municipality(municipality);
    }

    public List<RestaurantEntity> findAllRestaurantsInCity(String city) {
        return repository.findRestaurantsByLocation_City(city);
    }

    public RestaurantEntity findRestaurantByAddress(String address) {
        return repository.findRestaurantByLocation_Address(address)
                .orElseThrow(() -> new RestaurantNotFoundException("Could not find restaurant with address " +
                        address));
    }

    public RestaurantEntity findRestaurantByOwner(String email) {
        return repository.findRestaurantByOwner_Email(email)
                .orElseThrow(() -> new RestaurantNotFoundException("Could not find restaurant with owner email " +
                        email));
    }

    public boolean checkIfRestaurantExistsById(UUID id) {
        var found = repository.existsById(id);

        if (!found) {
            throw new RestaurantNotFoundException(id);
        }

        return true;
    }

    public void deleteRestaurantById(UUID id) {
        checkIfRestaurantExistsById(id);

        repository.deleteById(id);
    }

    public void deleteAllRestaurants() {
        repository.deleteAll();
    }

}
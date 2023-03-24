package com.ontime.crrs.persistence.restaurant.exception;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(String name) {
        super("Could not find restaurant with name: " + name);
    }

    public RestaurantNotFoundException(Integer id) {
        super("Could not find restaurant with ID: " + id);
    }

}
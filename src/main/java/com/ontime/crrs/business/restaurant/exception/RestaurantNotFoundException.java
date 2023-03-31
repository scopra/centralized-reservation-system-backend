package com.ontime.crrs.business.restaurant.exception;

import java.util.UUID;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(String name) {
        super("Could not find restaurant with name: " + name);
    }

    public RestaurantNotFoundException(UUID id) {
        super("Could not find restaurant with ID: " + id);
    }

}
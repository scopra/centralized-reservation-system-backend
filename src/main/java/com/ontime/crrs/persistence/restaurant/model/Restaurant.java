package com.ontime.crrs.persistence.restaurant.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Restaurant {

    private String name;
    private String description;
    private String phoneNumber;
    private int capacity;

}

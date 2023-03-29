package com.ontime.crrs.helper;

import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import org.springframework.stereotype.Component;

@Component
public class RestaurantTestHelper {

    public static RestaurantEntity buildDefaultEntityWithName(String name) {
        return RestaurantEntity.builder()
                .name(name)
                .phoneNumber("033/123-456")
                .capacity(25)
                .description("Default entity description.")
                .build();
    }
}
package com.ontime.crrs.helper;

import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import com.ontime.crrs.persistence.table.entity.TableEntity;
import org.springframework.stereotype.Component;

@Component
public class TableTestHelper {

    public static TableEntity buildDefaultEntityWithCapacity(int capacity, RestaurantEntity restaurant) {
        return TableEntity.builder()
                .capacity(capacity)
                .restaurant(restaurant)
                .build();
    }

}
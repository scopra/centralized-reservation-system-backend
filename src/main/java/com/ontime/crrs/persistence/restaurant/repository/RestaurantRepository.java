package com.ontime.crrs.persistence.restaurant.repository;

import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Integer> {
}
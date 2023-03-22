package com.ontime.crrs.persistence.restaurant.repository;

import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Integer> {

    Integer findRestaurantIdByName(String name);

    Optional<RestaurantEntity> findRestaurantByName(String name);
}
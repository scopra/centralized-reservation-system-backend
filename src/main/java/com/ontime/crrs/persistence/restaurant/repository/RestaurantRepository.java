package com.ontime.crrs.persistence.restaurant.repository;

import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, UUID> {

    @Query("SELECT r.id FROM RestaurantEntity r WHERE r.name = :name")
    UUID findRestaurantIdByName(@Param("name") String name);

    Optional<RestaurantEntity> findRestaurantByName(String name);
}
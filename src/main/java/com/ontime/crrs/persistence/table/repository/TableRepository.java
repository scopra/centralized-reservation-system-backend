package com.ontime.crrs.persistence.table.repository;

import com.ontime.crrs.persistence.table.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TableRepository extends JpaRepository<TableEntity, UUID> {

    List<TableEntity> findTablesByRestaurant_Name(String restaurantName);

    @Query("SELECT t.id FROM TableEntity t WHERE t.capacity = :capacity AND t.restaurant.name = :restaurantName")
    List<UUID> findTableIdsByCapacityAndRestaurant(int capacity, String restaurantName);

}
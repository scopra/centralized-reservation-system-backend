package com.ontime.crrs.persistence.table.repository;

import com.ontime.crrs.persistence.table.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TableRepository extends JpaRepository<TableEntity, UUID> {

    List<TableEntity> findTablesByRestaurant_Name(String restaurantName);

    List<TableEntity> findTablesByCapacity(int capacity);

}
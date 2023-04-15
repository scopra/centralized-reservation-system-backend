package com.ontime.crrs.persistence.table.service;

import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import com.ontime.crrs.persistence.table.entity.TableEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface TableService {

    TableEntity findTableById(UUID id);

    boolean checkIfRestaurantExistsById(UUID id);

    void deleteTableById(UUID id);

    void deleteAllTables();

   List<TableEntity> findAllTables();
}

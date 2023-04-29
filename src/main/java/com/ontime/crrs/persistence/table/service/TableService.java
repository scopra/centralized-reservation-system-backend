package com.ontime.crrs.persistence.table.service;

import com.ontime.crrs.persistence.table.entity.TableEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface TableService {

    TableEntity addTable(TableEntity table);

    TableEntity findTableById(UUID id);

    boolean checkIfTableExistsById(UUID id);

    void deleteTableById(UUID id);

    void deleteAllTables();

    List<TableEntity> findAllTables();

    List<TableEntity> findTablesByRestaurant(String restaurantName);

    List<TableEntity> findTablesForCapacity(int capacity);

}
package com.ontime.crrs.persistence.table.service;

import com.ontime.crrs.business.table.exception.TableNotFoundException;
import com.ontime.crrs.persistence.table.entity.TableEntity;
import com.ontime.crrs.persistence.table.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {

    private final TableRepository repository;

    public TableEntity addTable(TableEntity table) {
        return repository.save(table);
    }

    public TableEntity findTableById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new TableNotFoundException(id));
    }

    public boolean checkIfTableExistsById(UUID id) {
        var found = repository.existsById(id);

        if (!found) {
            throw new TableNotFoundException(id);
        }

        return true;
    }

    public void deleteTableById(UUID id) {
        checkIfTableExistsById(id);

        repository.deleteById(id);
    }

    public void deleteAllTables() {
        repository.deleteAll();
    }

    public List<TableEntity> findAllTables() {
        return repository.findAll();
    }

    public List<TableEntity> findTablesByRestaurant(String restaurantName) {
        return repository.findTablesByRestaurant_Name(restaurantName);
    }

    @Override
    public List<UUID> findTableIdsByCapacityAndRestaurant(int capacity, String name) {
        return repository.findTableIdsByCapacityAndRestaurant(capacity, name);
    }

}
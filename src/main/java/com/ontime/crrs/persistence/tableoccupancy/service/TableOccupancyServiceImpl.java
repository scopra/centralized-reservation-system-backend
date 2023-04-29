package com.ontime.crrs.persistence.tableoccupancy.service;

import com.ontime.crrs.persistence.tableoccupancy.entity.TableOccupancyEntity;
import com.ontime.crrs.persistence.tableoccupancy.repository.TableOccupancyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TableOccupancyServiceImpl implements TableOccupancyService {

    private final TableOccupancyRepository repository;

    public TableOccupancyEntity getTableOccupancyById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Table occupancy for ID " + id + " not found."));
    }

    public List<TableOccupancyEntity> getOccupanciesForTable(UUID id) {
        return repository.getTableOccupanciesByTable_Id(id);
    }

}
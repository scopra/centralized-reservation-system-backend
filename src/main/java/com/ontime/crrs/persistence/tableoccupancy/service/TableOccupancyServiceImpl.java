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

    private final TableOccupancyRepository occupancyRepository;

    public void occupyTable(TableOccupancyEntity tableOccupancy) {
        occupancyRepository.save(tableOccupancy);
    }

    public void freeUpTable(TableOccupancyEntity tableOccupancy) {
        if (!checkIfOccupancyExists(tableOccupancy)) {
            throw new EntityNotFoundException("Table occupancy does not exist.");
        }

        occupancyRepository.deleteById(tableOccupancy.getId());
    }

    public boolean checkIfOccupancyExists(TableOccupancyEntity tableOccupancy) {
        return occupancyRepository.existsById(tableOccupancy.getId());
    }

    public TableOccupancyEntity getTableOccupancyById(UUID id) {
        return occupancyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Table occupancy for ID " + id + " not found."));
    }

    public List<TableOccupancyEntity> getOccupanciesForTable(UUID id) {
        return occupancyRepository.getTableOccupanciesByTable_Id(id);
    }

    public void deleteAllOccupancies() {
        occupancyRepository.deleteAll();
    }

}
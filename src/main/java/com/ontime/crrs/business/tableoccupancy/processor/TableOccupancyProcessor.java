package com.ontime.crrs.business.tableoccupancy.processor;

import com.ontime.crrs.business.table.exception.TableUnavailableException;
import com.ontime.crrs.business.table.model.Table;
import com.ontime.crrs.business.tableoccupancy.model.TableOccupancy;
import com.ontime.crrs.persistence.table.entity.TableEntity;
import com.ontime.crrs.persistence.table.service.TableService;
import com.ontime.crrs.persistence.tableoccupancy.entity.TableOccupancyEntity;
import com.ontime.crrs.persistence.tableoccupancy.service.TableOccupancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TableOccupancyProcessor {

    private final TableService tableService;
    private final TableOccupancyService tableOccupancyService;

    public Table findFreeTable(String restaurantName, int capacity, LocalDate date) {
        var tables = findTablesForCapacity(restaurantName, capacity);


        return null;
    }

    private List<UUID> findTablesForCapacity(String restaurantName, int capacity) {
        var tableIds = tableService.findTablesByCapacityAndRestaurant(capacity, restaurantName);

        if (tableIds.isEmpty()) {
            throw new TableUnavailableException(restaurantName, capacity);
        }

        return tableIds;
    }

    private List<TableOccupancyEntity> findOccupanciesForDate(List<UUID> tableIds, LocalDate reservationDate) {
        return tableIds.stream()
                .map(tableOccupancyService::getOccupanciesForTable)
                .flatMap(Collection::stream)
                .filter(occupancy -> occupancy.getReservationDate().isEqual(reservationDate))
                .toList();
    }

    public boolean tableOccupiedForTime(LocalTime time, TableOccupancy tableOccupancy) {

        return time.isAfter(tableOccupancy.getReservedFrom()) && time.isBefore(tableOccupancy.getReservedTo());
    }

}
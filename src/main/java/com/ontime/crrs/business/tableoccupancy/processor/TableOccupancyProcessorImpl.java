package com.ontime.crrs.business.tableoccupancy.processor;

import com.ontime.crrs.business.mapper.restaurant.RestaurantMapper;
import com.ontime.crrs.business.mapper.table.TableMapper;
import com.ontime.crrs.business.reservation.model.Reservation;
import com.ontime.crrs.business.table.exception.TableUnavailableException;
import com.ontime.crrs.business.table.model.Table;
import com.ontime.crrs.persistence.restaurant.service.RestaurantService;
import com.ontime.crrs.persistence.table.entity.TableEntity;
import com.ontime.crrs.persistence.table.service.TableService;
import com.ontime.crrs.persistence.tableoccupancy.entity.TableOccupancyEntity;
import com.ontime.crrs.persistence.tableoccupancy.service.TableOccupancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TableOccupancyProcessorImpl implements TableOccupancyProcessor {

    private final TableService tableService;
    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;
    private final TableOccupancyService tableOccupancyService;
    private final TableMapper tableMapper;

    public Table assignTable(Reservation reservation) {
        var restaurant = restaurantService.findRestaurantByName(reservation.getRestaurant().getName());
        reservation.setRestaurant(restaurantMapper.entityToModel(restaurant));

        var tableOccupanciesMap = mapTablesToOccupancies(findTablesForCapacity(restaurant.getName(),
                reservation.getCapacity()), reservation.getDate());

        var table = retrieveTable(tableOccupanciesMap, reservation);

        tableOccupancyService.occupyTable(buildTableOccupancyEntity(reservation, table));

        return tableMapper.entityToModel(table);
    }

    private List<UUID> findTablesForCapacity(String restaurantName, int capacity) {
        var tableIds = tableService.findTableIdsByCapacityAndRestaurant(capacity, restaurantName);

        if (tableIds.isEmpty()) {
            throw new TableUnavailableException(restaurantName, capacity);
        }

        return tableIds;
    }

    private Map<UUID, List<TableOccupancyEntity>> mapTablesToOccupancies(List<UUID> tableIds, LocalDate reservationDate) {
        return tableIds.stream()
                .collect(Collectors.toMap(Function.identity(), tableOccupancyService::getOccupanciesForTable))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .filter(occupancy -> occupancy.getReservationDate().isEqual(reservationDate))
                                .collect(Collectors.toList())));
    }

    private List<UUID> getTablesWithNoReservations(Map<UUID, List<TableOccupancyEntity>> map) {
        return map.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isEmpty())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private UUID findFreeTable(Map<UUID, List<TableOccupancyEntity>> tableOccupancies, LocalTime start, LocalTime end) {
        return tableOccupancies.entrySet().stream()
                .filter(table -> table.getValue().stream().allMatch(occupancy -> confirmNotOverlapping(occupancy, start, end)))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new TableUnavailableException("No available time was found for table."));

    }

    private boolean confirmNotOverlapping(TableOccupancyEntity tableOccupancy, LocalTime start, LocalTime end) {
        var reservedFrom = tableOccupancy.getReservedFrom();
        var reservedTo = tableOccupancy.getReservedTo();

        return (start.isBefore(reservedFrom) && (end.isBefore(reservedFrom) || end.equals(reservedFrom)))
                || (start.isAfter(reservedTo) || start.equals(reservedTo));
    }

    private TableEntity retrieveTable(Map<UUID, List<TableOccupancyEntity>> tableOccupanciesMap, Reservation reservation) {
        var tablesWithNoReservations = getTablesWithNoReservations(tableOccupanciesMap);

        if (!tablesWithNoReservations.isEmpty()) {
            return tableService.findTableById(tablesWithNoReservations.get(0));
        } else {
            return tableService.findTableById(findFreeTable(tableOccupanciesMap,
                    reservation.getStartTime(), reservation.getEndTime()));
        }
    }

    private TableOccupancyEntity buildTableOccupancyEntity(Reservation reservation, TableEntity table) {
        return TableOccupancyEntity.builder()
                .reservationDate(reservation.getDate())
                .reservedFrom(reservation.getStartTime())
                .reservedTo(reservation.getEndTime())
                .table(table)
                .build();
    }

}
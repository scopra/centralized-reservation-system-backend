package com.ontime.crrs.business.table.helper;

import com.ontime.crrs.business.mapper.table.TableMapper;
import com.ontime.crrs.business.restaurant.model.RestaurantCreationRequest;
import com.ontime.crrs.business.table.model.Table;
import com.ontime.crrs.persistence.restaurant.service.RestaurantService;
import com.ontime.crrs.persistence.table.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TableHelper {

    private final TableService tableService;
    private final RestaurantService restaurantService;
    private final TableMapper tableMapper;

    public List<Table> addTables(RestaurantCreationRequest creationRequest) {
        var tableEntities = tableMapper.modelsToEntities(creationRequest.getTables());
        var restaurantEntity = restaurantService.findRestaurantByName(creationRequest.getName());

        var addedTables = tableEntities.stream()
                .peek(tableEntity -> tableEntity.setRestaurant(restaurantEntity))
                .map(tableService::addTable)
                .toList();

        return tableMapper.entitiesToModels(addedTables);
    }

}
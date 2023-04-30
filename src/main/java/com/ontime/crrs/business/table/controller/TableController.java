package com.ontime.crrs.business.table.controller;

import com.ontime.crrs.business.mapper.table.TableMapper;
import com.ontime.crrs.business.table.model.Table;
import com.ontime.crrs.persistence.restaurant.service.RestaurantService;
import com.ontime.crrs.persistence.table.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tables")
public class TableController {

    private final TableService tableService;
    private final RestaurantService restaurantService;
    private final TableMapper mapper;
//
//    @GetMapping
//    public CollectionModel<Table> getTables() {
//        var tables = mapper.entitiesToModels(tableService.findAllTables());
//
//        return CollectionModel.of(tables, linkTo(methodOn(TableController.class).getTables()).withSelfRel());
//    }
//
    @GetMapping("/owner/restaurant/{restaurantName}")
    public CollectionModel<Table> getAllTablesForRestaurant(@PathVariable String restaurantName) {
        var tables = mapper.entitiesToModels(tableService.findTablesByRestaurant(restaurantName));

        return CollectionModel.of(tables,
                linkTo(methodOn(TableController.class).getAllTablesForRestaurant(restaurantName)).withSelfRel());
    }

    @PostMapping("/owner/add/{restaurantName}")
    public ResponseEntity<?> addTableForRestaurant(@PathVariable String restaurantName, @RequestBody Table table) {
        var restaurant = restaurantService.findRestaurantByName(restaurantName);
        var tableEntity = mapper.modelToEntity(table);
        tableEntity.setRestaurant(restaurant);

        var savedTable =  tableService.addTable(tableEntity);
        //var location = uriBuilder.path("/tables/{id}").buildAndExpand(createdTable.getId()).toUri();
        var tableModel = mapper.entityToModel(savedTable);

        //return ResponseEntity.created(location).build();
        return null;
    }

    @DeleteMapping("/owner/delete/{id}")
    public ResponseEntity<?> deleteTable(@PathVariable UUID id) {
        tableService.deleteTableById(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/owner/delete")
    public ResponseEntity<?> deleteAllTables() {
        tableService.deleteAllTables();

        return ResponseEntity
                .noContent()
                .build();
    }
}
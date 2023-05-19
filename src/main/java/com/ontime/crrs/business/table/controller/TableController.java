package com.ontime.crrs.business.table.controller;

import com.ontime.crrs.business.mapper.table.TableMapper;
import com.ontime.crrs.business.table.model.Table;
import com.ontime.crrs.business.table.model.TableModelAssembler;
import com.ontime.crrs.persistence.restaurant.service.RestaurantService;
import com.ontime.crrs.persistence.table.service.TableService;
import com.ontime.crrs.persistence.tableoccupancy.service.TableOccupancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tables")
public class TableController {

    private final TableService tableService;
    private final RestaurantService restaurantService;
    private final TableOccupancyService tableOccupancyService;
    private final TableMapper mapper;
    private final TableModelAssembler modelAssembler;

    @GetMapping("/owner/id/{id}")
    public EntityModel<?> getTableById(@PathVariable UUID id) {
        var table = mapper.entityToModel(tableService.findTableById(id));

        return modelAssembler.toModel(table);
    }

    @GetMapping("/owner/{restaurantName}")
    public CollectionModel<Table> getAllTablesForRestaurant(@PathVariable String restaurantName) {
        var tables = mapper.entitiesToModels(tableService.findTablesByRestaurant(restaurantName));

        return CollectionModel.of(tables,
                linkTo(methodOn(TableController.class).getAllTablesForRestaurant(restaurantName)).withSelfRel());
    }

    @PostMapping("/owner/{restaurantName}")
    public EntityModel<Table> addTableForRestaurant(@PathVariable String restaurantName, @RequestBody Table table) {
        var tableEntity = mapper.modelToEntity(table);
        tableEntity.setRestaurant(restaurantService.findRestaurantByName(restaurantName));

        var tableModel = mapper.entityToModel(tableService.addTable(tableEntity));

        return modelAssembler.toModel(tableModel);
    }

    @DeleteMapping("/owner/{id}")
    public ResponseEntity<?> deleteTable(@PathVariable UUID id) {
        tableService.deleteTableById(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/owner")
    public ResponseEntity<?> deleteAllTables() {
        tableService.deleteAllTables();

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/admin/occupancy")
    public ResponseEntity<?> deleteAllOccupancies() {
        tableOccupancyService.deleteAllOccupancies();

        return ResponseEntity
                .noContent()
                .build();
    }

}
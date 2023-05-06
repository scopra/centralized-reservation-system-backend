package com.ontime.crrs.business.table.controller;

import com.ontime.crrs.business.mapper.table.TableMapper;
import com.ontime.crrs.business.mapper.tableoccupancy.TableOccupancyMapper;
import com.ontime.crrs.business.reservation.model.Reservation;
import com.ontime.crrs.business.table.model.Table;
import com.ontime.crrs.business.table.model.TableModelAssembler;
import com.ontime.crrs.business.tableoccupancy.model.TableOccupancy;
import com.ontime.crrs.business.tableoccupancy.processor.TableOccupancyProcessor;
import com.ontime.crrs.persistence.restaurant.service.RestaurantService;
import com.ontime.crrs.persistence.table.service.TableService;
import com.ontime.crrs.persistence.tableoccupancy.service.TableOccupancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    private final TableOccupancyMapper tableOccupancyMapper;
    private final TableMapper mapper;
    private final TableModelAssembler modelAssembler;
    private final TableOccupancyProcessor processor;

    //RADI
    @GetMapping("/owner/id/{id}")
    public EntityModel<?> getTableById(@PathVariable UUID id) {
        var table = mapper.entityToModel(tableService.findTableById(id));

        return modelAssembler.toModel(table);
    }

    //RADI
    @GetMapping("/owner/{restaurantName}")
    public CollectionModel<Table> getAllTablesForRestaurant(@PathVariable String restaurantName) {
        var tables = mapper.entitiesToModels(tableService.findTablesByRestaurant(restaurantName));

        return CollectionModel.of(tables,
                linkTo(methodOn(TableController.class).getAllTablesForRestaurant(restaurantName)).withSelfRel());
    }

    //RADI
    @PostMapping("/owner/{restaurantName}")
    public EntityModel<Table> addTableForRestaurant(@PathVariable String restaurantName, @RequestBody Table table) {
        var tableEntity = mapper.modelToEntity(table);
        tableEntity.setRestaurant(restaurantService.findRestaurantByName(restaurantName));

        var tableModel = mapper.entityToModel(tableService.addTable(tableEntity));

        return modelAssembler.toModel(tableModel);
    }

    //RADI
    @DeleteMapping("/owner/{id}")
    public ResponseEntity<?> deleteTable(@PathVariable UUID id) {
        tableService.deleteTableById(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    //RADI
    @DeleteMapping("/owner")
    public ResponseEntity<?> deleteAllTables() {
        tableService.deleteAllTables();

        return ResponseEntity
                .noContent()
                .build();
    }

    //TEST
    @GetMapping("/test")
    public void testMethod() {

    }

    //TEST
    @PostMapping("/test/occupancy/{name}")
    public ResponseEntity<?> testAddingOccupancy(@PathVariable String name, @RequestBody TableOccupancy tableOccupancy) {
        var table = tableService.findTableById(UUID.fromString("79fd0c81-a1bb-4d3e-b72a-3b7f27f3d4d3"));
        tableOccupancy.setTable(mapper.entityToModel(table));

        var entityToSave = tableOccupancyMapper.modelToEntity(tableOccupancy);
        entityToSave.setTable(table);

        tableOccupancyService.occupyTable(entityToSave);

        return ResponseEntity.ok(tableOccupancyMapper.entityToModel(entityToSave));
    }

    @GetMapping("/test/occupancy/{name}")
    public ResponseEntity<?> findTablesForCapacityTest(@PathVariable String name) {
        var list = processor.findTablesForCapacity(name, 8);
        return ResponseEntity.ok(tableOccupancyMapper.entitiesToModels(processor.findOccupanciesForDate(list,
                LocalDate.of(2023, 5, 14))));
    }

    @GetMapping("/test/reserve/{restaurantName}")
    public ResponseEntity<Table> reserveTable(@PathVariable String restaurantName, @RequestBody Reservation reservation) {
        return ResponseEntity.ok(processor.assignTable(restaurantName, reservation));
    }

//
//    @GetMapping("/test/1/occupancy/{name}")
//    public void tesssssst(@PathVariable String name) {
//        var list = processor.findTablesForCapacity(name, 5);
//
//        var map = processor.mapTablesToOccupancies(list, LocalDate.of(2023, 10, 1));
//        System.out.println("\n----------\n" + map + "\n----------\n");
//
//        var x = processor.getTablesWithNoReservations(map);
//        System.out.println("\n----------\n" + x + "\n----------\n");
//    }

}
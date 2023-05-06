package com.ontime.crrs.business.table.controller;

import com.ontime.crrs.business.mapper.table.TableMapper;
import com.ontime.crrs.business.table.model.Table;
import com.ontime.crrs.persistence.table.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tables")
public class TableController {
    private final TableService tableService;
    private final TableMapper mapper;

    @GetMapping
    public CollectionModel<Table> getTables() {
        var tables = mapper.entitiesToModels(tableService.findAllTables()).stream().toList();

        return CollectionModel.of(tables, linkTo(methodOn(TableController.class).getTables()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<?> addTable(@RequestBody Table table, UriComponentsBuilder uriBuilder) {
        var tableEntity = mapper.modelToEntity(table);

        var createdTable = tableService.addTable(tableEntity);
        var location = uriBuilder.path("/tables/{id}").buildAndExpand(createdTable.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<?> deleteTable(@PathVariable UUID id) {
        tableService.deleteTableById(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/admin")
    public ResponseEntity<?> deleteAllTables() {
        tableService.deleteAllTables();

        return ResponseEntity
                .noContent()
                .build();
    }
}

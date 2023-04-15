package com.ontime.crrs.business.table.controller;

import com.ontime.crrs.business.mapper.table.TableMapper;
import com.ontime.crrs.business.table.model.Table;
import com.ontime.crrs.persistence.table.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tables")
public class TableController {
    private final TableService tableService;
    private final TableMapper mapper;

   // private final TableModel

    @GetMapping
    public CollectionModel<Table> getTables() {
        var tables = mapper.entitiesToModels(tableService.findAllTables()).stream().toList();

        return CollectionModel.of(tables, linkTo(methodOn(TableController.class).getTables()).withSelfRel());
    }

}

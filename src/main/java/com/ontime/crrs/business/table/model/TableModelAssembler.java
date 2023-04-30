package com.ontime.crrs.business.table.model;

import com.ontime.crrs.business.table.controller.TableController;
import io.micrometer.common.lang.NonNullApi;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@NonNullApi
@RequiredArgsConstructor
public class TableModelAssembler implements RepresentationModelAssembler<Table, EntityModel<Table>> {
    @Override
    public EntityModel<Table> toModel(Table table) {

        return EntityModel.of(table,
                linkTo(methodOn(TableController.class).getTableById(table.getId())).withSelfRel(),
                linkTo(methodOn(TableController.class).getAllTablesForRestaurant(table.getRestaurant().getName())).withRel("restaurants"));

    }
}

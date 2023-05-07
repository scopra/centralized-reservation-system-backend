package com.ontime.crrs.business.menuitem.model;

import com.ontime.crrs.business.menuitem.controller.MenuItemController;
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
public class MenuItemModelAssembler implements RepresentationModelAssembler<MenuItem, EntityModel<MenuItem>> {

    @Override
    public EntityModel<MenuItem> toModel(MenuItem entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(MenuItemController.class).getMenuItemForRestaurant(entity.getRestaurant().getName(), entity)).withSelfRel(),
                linkTo(methodOn(MenuItemController.class).getMenuItemsForRestaurant(entity.getRestaurant().getName())).withRel("menuItems"));
    }

}
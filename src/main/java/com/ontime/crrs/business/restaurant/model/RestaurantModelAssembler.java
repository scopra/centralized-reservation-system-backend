package com.ontime.crrs.business.restaurant.model;

import com.ontime.crrs.business.restaurant.controller.RestaurantController;
import com.ontime.crrs.business.table.controller.TableController;
import io.micrometer.common.lang.NonNullApi;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@NonNullApi
@RequiredArgsConstructor
public class RestaurantModelAssembler implements RepresentationModelAssembler<Restaurant, EntityModel<Restaurant>> {

    @Override
    public EntityModel<Restaurant> toModel(Restaurant restaurant) {

        return EntityModel.of(restaurant,
                linkTo(methodOn(RestaurantController.class).getRestaurantByName(restaurant.getName())).withSelfRel(),
                linkTo(methodOn(RestaurantController.class).getRestaurants()).withRel("restaurants"));
    }

    public EntityModel<Restaurant> toAdminModel(Restaurant restaurant, UUID id) {

        return EntityModel.of(restaurant,
                linkTo(methodOn(RestaurantController.class).getRestaurantById(id)).withSelfRel(),
                linkTo(methodOn(RestaurantController.class).getRestaurants()).withRel("restaurants"));
    }

    public EntityModel<RestaurantInformation> toModel(RestaurantInformation restaurant) {
        var name = restaurant.getRestaurant().getName();
        return EntityModel.of(restaurant,
                linkTo(methodOn(RestaurantController.class).getRestaurantByName(name)).withSelfRel(),
                linkTo(methodOn(TableController.class).getAllTablesForRestaurant(name)).withRel("tables"),
                //TODO: add menu items
                linkTo(methodOn(RestaurantController.class).getRestaurants()).withRel("restaurants"));
    }

}
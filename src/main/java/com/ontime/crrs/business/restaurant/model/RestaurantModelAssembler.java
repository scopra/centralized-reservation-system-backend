package com.ontime.crrs.business.restaurant.model;

import com.ontime.crrs.business.restaurant.controller.RestaurantController;
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

    public EntityModel<RestaurantCreationResponse> toModel(RestaurantCreationResponse restaurant) {

        return EntityModel.of(restaurant,
                linkTo(methodOn(RestaurantController.class).getRestaurantByName(restaurant.getRestaurant().getName())).withSelfRel(),
                linkTo(methodOn(RestaurantController.class).getRestaurants()).withRel("restaurants"));
    }

}
package com.ontime.crrs.persistence.restaurant.model;

import com.ontime.crrs.persistence.restaurant.controller.RestaurantController;
import com.ontime.crrs.persistence.restaurant.service.RestaurantService;
import io.micrometer.common.lang.NonNullApi;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@NonNullApi
@RequiredArgsConstructor
public class RestaurantModelAssembler implements RepresentationModelAssembler<Restaurant, EntityModel<Restaurant>> {

    private final RestaurantService restaurantService;

    @Override
    public EntityModel<Restaurant> toModel(Restaurant entity) {
        var restaurantModel = EntityModel.of(entity);

        restaurantModel.add(linkTo(methodOn(RestaurantController.class)
                .getRestaurantById(restaurantService.findRestaurantIdByName(entity.getName())))
                .withSelfRel());

        restaurantModel.add(linkTo(methodOn(RestaurantController.class)
                .getRestaurants())
                .withRel(IanaLinkRelations.COLLECTION));

        return restaurantModel;
    }

}
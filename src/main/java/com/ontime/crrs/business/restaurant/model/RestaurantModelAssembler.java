package com.ontime.crrs.business.restaurant.model;

import com.ontime.crrs.business.restaurant.controller.RestaurantController;
import com.ontime.crrs.persistence.restaurant.service.RestaurantService;
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
public class RestaurantModelAssembler implements RepresentationModelAssembler<Restaurant, EntityModel<Restaurant>> {

    private final RestaurantService restaurantService;

    @Override
    public EntityModel<Restaurant> toModel(Restaurant restaurant) {

        /* Ne radi al bi trebalo da se vidi ID u self ref linku, sto je nemoguce kod nas jer ne zelimo da exposamo ID
            return EntityModel.of(restaurant,
                    linkTo(methodOn(RestaurantController.class).getRestaurantById(entityId)).withSelfRel(),
                    linkTo(methodOn(RestaurantController.class).getRestaurants()).withRel("restaurants"));

         */

        return EntityModel.of(restaurant,
                linkTo(methodOn(RestaurantController.class).getRestaurantByName(restaurant.getName())).withSelfRel(),
                linkTo(methodOn(RestaurantController.class).getRestaurants()).withRel("restaurants"));
    }

}
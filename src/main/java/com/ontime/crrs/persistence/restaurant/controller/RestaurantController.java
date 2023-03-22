package com.ontime.crrs.persistence.restaurant.controller;

import com.ontime.crrs.persistence.restaurant.exception.RestaurantNotFoundException;
import com.ontime.crrs.persistence.restaurant.helper.RestaurantHelper;
import com.ontime.crrs.persistence.restaurant.model.Restaurant;
import com.ontime.crrs.persistence.restaurant.model.RestaurantModelAssembler;
import com.ontime.crrs.persistence.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantModelAssembler restaurantModelAssembler;
    private final RestaurantHelper restaurantHelper;

    /*
        GET /restaurants - Returns a list of all restaurants in the database.
        GET /restaurants/{id} - Returns a specific restaurant by its ID.
        POST /restaurants - Creates a new restaurant in the database.
        PUT /restaurants/{id} - Updates a specific restaurant by its ID.
        DELETE /restaurants/{id} - Deletes a specific restaurant by its ID.
     */

    @GetMapping
    public CollectionModel<EntityModel<Restaurant>> getRestaurants() {
        List<EntityModel<Restaurant>> restaurants =
                restaurantHelper.mapListOfEntitiesToListOfModels(restaurantService.findAllRestaurants()).stream()
                        .map(restaurantModelAssembler::toModel)
                        .collect(Collectors.toList());

        return CollectionModel.of(restaurants, linkTo(methodOn(RestaurantController.class).getRestaurants()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Restaurant> getRestaurantById(@PathVariable Integer id) {
        var restaurantEntity = restaurantService.findRestaurantById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));

        var restaurantModel = restaurantHelper.mapRestaurantEntityToModel(restaurantEntity);

        return restaurantModelAssembler.toModel(restaurantModel);
    }

    @GetMapping("/{name}")
    public EntityModel<Restaurant> getRestaurantByName(@PathVariable String name) {
        var restaurantEntity = restaurantService.findRestaurantByName(name)
                .orElseThrow(() -> new RestaurantNotFoundException(name));

        var restaurantModel = restaurantHelper.mapRestaurantEntityToModel(restaurantEntity);

        return restaurantModelAssembler.toModel(restaurantModel);
    }

    @PutMapping("/{name}")
    public ResponseEntity<?> updateRestaurant(@RequestBody Restaurant newRestaurant, @PathVariable String name) {
        var updatedRestaurant = restaurantService.findRestaurantByName(name)
                .map(restaurant -> {
                    copyProperties(newRestaurant, restaurant);

                    return restaurantService.updateRestaurant(restaurant);
                })
                .orElseThrow(() -> new RestaurantNotFoundException(name));

        EntityModel<Restaurant> entityModel = restaurantModelAssembler.toModel(newRestaurant);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PostMapping
    public ResponseEntity<?> addRestaurant(@RequestBody Restaurant restaurant) {
        var restaurantEntity = restaurantHelper.mapRestaurantModelToEntity(restaurant);

        restaurantService.updateRestaurant(restaurantEntity);

        EntityModel<Restaurant> entityModel = restaurantModelAssembler.toModel(restaurant);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable Integer id) {

        restaurantService.deleteRestaurantById(id);

        return ResponseEntity
                .noContent()
                .build();
    }

}
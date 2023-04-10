package com.ontime.crrs.business.restaurant.controller;

import com.ontime.crrs.business.mapper.restaurant.RestaurantMap;
import com.ontime.crrs.business.restaurant.exception.RestaurantNotFoundException;
import com.ontime.crrs.business.restaurant.model.Restaurant;
import com.ontime.crrs.business.restaurant.model.RestaurantModelAssembler;
import com.ontime.crrs.persistence.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantModelAssembler modelAssembler;
    private final RestaurantMap mapper;

    //RADI + novi mapper
    @GetMapping
    public CollectionModel<EntityModel<Restaurant>> getRestaurants() {
        var restaurants =
                mapper.entitiesToModels(restaurantService.findAllRestaurants()).stream()
                        .map(modelAssembler::toModel)
                        .collect(Collectors.toList());

        return CollectionModel.of(restaurants, linkTo(methodOn(RestaurantController.class).getRestaurants()).withSelfRel());
    }

    //NE RADI, ADMIN method
    @GetMapping("/admin/id/{id}")
    public EntityModel<Restaurant> getRestaurantByIdAdmin(@PathVariable UUID id) {
        var restaurantEntity = restaurantService.findRestaurantById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));

        //var restaurantModel = mapper.mapEntityToModel(restaurantEntity);
        var restaurantModel = mapper.entityToModel(restaurantEntity);

        return modelAssembler.toAdminModel(restaurantModel, restaurantEntity.getId());
    }

    //RADI
    @GetMapping("/{name}")
    public EntityModel<Restaurant> getRestaurantByName(@PathVariable String name) {
        var restaurantEntity = restaurantService.findRestaurantByName(name)
                .orElseThrow(() -> new RestaurantNotFoundException(name));

        var restaurantModel = mapper.entityToModel(restaurantEntity);

        return modelAssembler.toModel(restaurantModel);
    }

    //RADI: custom update with fields, PATCH mapping?
    @PutMapping("/{name}")
    public ResponseEntity<?> updateRestaurant(@RequestBody Restaurant newRestaurant, @PathVariable String name) {
        var updatedRestaurant = restaurantService.findRestaurantByName(name)
                .map(restaurantEntity -> {
                    copyProperties(newRestaurant, restaurantEntity);
                    copyProperties(newRestaurant.getLocation(), restaurantEntity.getLocation());
                    return restaurantService.updateRestaurant(restaurantEntity);
                })
                .orElseThrow(() -> new RestaurantNotFoundException(name));

        var restaurantModel = mapper.entityToModel(updatedRestaurant);

        EntityModel<Restaurant> entityModel = modelAssembler.toModel(restaurantModel);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    //RADI
    @PostMapping
    public ResponseEntity<?> addRestaurant(@RequestBody Restaurant restaurant) {
        //var restaurantEntity = mapper.mapModelToEntity(restaurant);
        var restaurantEntity = mapper.modelToEntity(restaurant);

        restaurantService.updateRestaurant(restaurantEntity);

        var entityModel = modelAssembler.toModel(restaurant);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    //RADI
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable UUID id) {
        if (!restaurantService.checkIfRestaurantExists(id)) {
            throw new RestaurantNotFoundException(id);
        }

        restaurantService.deleteRestaurantById(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    //RADI, ADMIN method
    @DeleteMapping
    public ResponseEntity<?> deleteAllRestaurants() {
        restaurantService.deleteAllRestaurants();

        return ResponseEntity
                .noContent()
                .build();
    }

    //RADI, admin method!!!
    @GetMapping("/admin/name/{name}")
    public UUID findRestaurantIDByName(@PathVariable String name) {
        var id = restaurantService.findRestaurantIdByName(name);

        if (!restaurantService.checkIfRestaurantExists(id)) {
            throw new RestaurantNotFoundException(id);
        }

        return id;
    }

    //TESTING
    @PostMapping("/test")
    public Restaurant addRestaurantTest(@RequestBody Restaurant restaurant) {
        var restaurantEntity = mapper.modelToEntity(restaurant);

        restaurantService.updateRestaurant(restaurantEntity);

        return restaurant;
    }

    @GetMapping("/test/id/{id}")
    public Boolean checkIfExists(@PathVariable UUID id) {
        return restaurantService.checkIfRestaurantExists(id);
    }


    @GetMapping("/test/name/{name}")
    public UUID findRestIDByName(@PathVariable String name) {
        var id = restaurantService.findRestaurantIdByName(name);

        return id;
    }


    @GetMapping("/test/mapper")
    public Restaurant addNew(@RequestBody Restaurant model) {
        var entity = mapper.modelToEntity(model);

        restaurantService.updateRestaurant(entity);

        return model;
    }


}
package com.ontime.crrs.business.restaurant.controller;

import com.ontime.crrs.business.mapper.restaurant.RestaurantMapper;
import com.ontime.crrs.business.restaurant.model.Restaurant;
import com.ontime.crrs.business.restaurant.model.RestaurantModelAssembler;
import com.ontime.crrs.business.restaurant.processor.RestaurantHelper;
import com.ontime.crrs.persistence.restaurant.service.RestaurantService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantModelAssembler modelAssembler;
    private final RestaurantMapper mapper;
    private final RestaurantHelper restaurantHelper;

    @GetMapping
    public CollectionModel<EntityModel<Restaurant>> getRestaurants() {
        var restaurants =
                mapper.entitiesToModels(restaurantService.findAllRestaurants()).stream()
                        .map(modelAssembler::toModel)
                        .toList();

        return CollectionModel.of(restaurants, linkTo(methodOn(RestaurantController.class)
                .getRestaurants()).withSelfRel());
    }

    @GetMapping("/{name}")
    public EntityModel<Restaurant> getRestaurantByName(@PathVariable String name) {
        var restaurantEntity = restaurantService.findRestaurantByName(name);

        var restaurantModel = mapper.entityToModel(restaurantEntity);

        return modelAssembler.toModel(restaurantModel);
    }

    @GetMapping("/address/{address}")
    public EntityModel<Restaurant> getRestaurantByAddress(@PathVariable String address) {
        var restaurantEntity = restaurantService.findRestaurantByAddress(address);

        var restaurantModel = mapper.entityToModel(restaurantEntity);

        return modelAssembler.toModel(restaurantModel);
    }

    @GetMapping("/municipality/{municipality}")
    public CollectionModel<EntityModel<Restaurant>> getRestaurantsByMunicipality(@PathVariable String municipality) {
        var restaurants =
                mapper.entitiesToModels(restaurantService.findAllRestaurantsInMunicipality(municipality)).stream()
                        .map(modelAssembler::toModel)
                        .toList();

        return CollectionModel.of(restaurants, linkTo(methodOn(RestaurantController.class)
                .getRestaurantsByMunicipality(municipality)).withSelfRel());
    }

    @GetMapping("/city/{city}")
    public CollectionModel<EntityModel<Restaurant>> getRestaurantsByCity(@PathVariable String city) {
        var restaurants =
                mapper.entitiesToModels(restaurantService.findAllRestaurantsInCity(city)).stream()
                        .map(modelAssembler::toModel)
                        .toList();

        return CollectionModel.of(restaurants, linkTo(methodOn(RestaurantController.class)
                .getRestaurantsByCity(city)).withSelfRel());
    }

    @GetMapping("/admin/id/{id}")
    public EntityModel<Restaurant> getRestaurantById(@PathVariable UUID id) {
        var restaurantEntity = restaurantService.findRestaurantById(id);

        var restaurantModel = mapper.entityToModel(restaurantEntity);

        return modelAssembler.toAdminModel(restaurantModel, restaurantEntity.getId());
    }

    @GetMapping("/admin/name/{name}")
    public ResponseEntity<UUID> getRestaurantIDByName(@PathVariable String name) {
        var id = restaurantService.findRestaurantIdByName(name);

        return ResponseEntity.ok(id);
    }

    @PutMapping
    public ResponseEntity<?> updateRestaurant(HttpServletRequest request, @RequestBody Restaurant newRestaurant) {
        var updatedRestaurant = restaurantHelper.updateRestaurant(request, newRestaurant);

        var restaurantModel = mapper.entityToModel(updatedRestaurant);

        var entityModel = modelAssembler.toModel(restaurantModel);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PostMapping
    public ResponseEntity<?> addRestaurant(HttpServletRequest request, @RequestBody Restaurant restaurant) {
        var restaurantModel = restaurantHelper.saveRestaurant(request, restaurant);

        var entityModel = modelAssembler.toModel(restaurantModel);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/owner/{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable UUID id) {
        restaurantService.deleteRestaurantById(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/owner")
    public ResponseEntity<?> deleteAllRestaurants() {
        restaurantService.deleteAllRestaurants();

        return ResponseEntity
                .noContent()
                .build();
    }

}
package com.ontime.crrs.business.restaurant.controller;

import com.ontime.crrs.business.mapper.restaurant.RestaurantMapper;
import com.ontime.crrs.business.restaurant.helper.RestaurantHelper;
import com.ontime.crrs.business.restaurant.model.Restaurant;
import com.ontime.crrs.business.restaurant.model.RestaurantCreationRequest;
import com.ontime.crrs.business.restaurant.model.RestaurantInformation;
import com.ontime.crrs.business.restaurant.model.RestaurantModelAssembler;
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
                        .map(modelAssembler :: toModel)
                        .toList();

        return CollectionModel.of(restaurants, linkTo(methodOn(RestaurantController.class)
                .getRestaurants()).withSelfRel());
    }

    @GetMapping("/{name}")
    public EntityModel<RestaurantInformation> getRestaurantByName(@PathVariable String name) {
        var restaurantInfo = restaurantHelper.getRestaurantInformation(name);

        return modelAssembler.toInformationModel(restaurantInfo);
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
                        .map(modelAssembler :: toModel)
                        .toList();

        return CollectionModel.of(restaurants, linkTo(methodOn(RestaurantController.class)
                .getRestaurantsByMunicipality(municipality)).withSelfRel());
    }

    @GetMapping("/city/{city}")
    public CollectionModel<EntityModel<Restaurant>> getRestaurantsByCity(@PathVariable String city) {
        var restaurants =
                mapper.entitiesToModels(restaurantService.findAllRestaurantsInCity(city)).stream()
                        .map(modelAssembler :: toModel)
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

        var entityModel = modelAssembler.toModel(updatedRestaurant);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PostMapping
    public ResponseEntity<?> addRestaurant(HttpServletRequest request, @RequestBody RestaurantCreationRequest creationRequest) {
        var restaurant = restaurantHelper.processCreationRequest(request, creationRequest);

        var entityModel = modelAssembler.toModel(restaurant);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/owner")
    public ResponseEntity<?> deleteRestaurant(HttpServletRequest request) {
        var id = restaurantHelper.processRestaurantDeletion(request);

        restaurantService.deleteRestaurantById(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/admin")
    public ResponseEntity<?> deleteAllRestaurants() {
        restaurantService.deleteAllRestaurants();

        return ResponseEntity
                .noContent()
                .build();
    }

}
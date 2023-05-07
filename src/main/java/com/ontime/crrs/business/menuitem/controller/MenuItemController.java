package com.ontime.crrs.business.menuitem.controller;

import com.ontime.crrs.business.mapper.menuitem.MenuItemMapper;
import com.ontime.crrs.business.menuitem.helper.MenuItemHelper;
import com.ontime.crrs.business.menuitem.model.MenuItem;
import com.ontime.crrs.business.menuitem.model.MenuItemModelAssembler;
import com.ontime.crrs.persistence.menuitem.service.MenuItemService;
import com.ontime.crrs.persistence.menuitem.util.Category;
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
@RequestMapping("/menuitems")
public class MenuItemController {

    private final MenuItemService menuItemService;
    private final MenuItemModelAssembler modelAssembler;
    private final MenuItemHelper helper;
    private final MenuItemMapper mapper;

    //get all by restaurant, RADI
    @GetMapping("/all/{restaurantName}")
    public CollectionModel<EntityModel<MenuItem>> getMenuItemsForRestaurant(@PathVariable String restaurantName) {
        var restaurant = helper.confirmRestaurantExists(restaurantName);
        var menuItems =
                mapper.entitiesToModels((menuItemService.getMenuItemsForRestaurant(restaurant.getName()))).stream()
                        .map(modelAssembler::toModel)
                        .toList();

        return CollectionModel.of(menuItems, linkTo(methodOn(MenuItemController.class)
                .getMenuItemsForRestaurant(restaurantName)).withSelfRel());
    }

    //see by category, request can be string restaurnat name
    @GetMapping("/category/{category}")
    public CollectionModel<EntityModel<MenuItem>> getItemsByCategoryForRestaurant(@PathVariable Category category,
                                                                                  @RequestBody String restaurantName) {
        var restaurant = helper.confirmRestaurantExists(restaurantName);
        var items =
                mapper.entitiesToModels(menuItemService.getMenuItemsByCategoryAndRestaurantName(category, restaurant.getName()))
                        .stream()
                        .map(modelAssembler::toModel)
                        .toList();

        return CollectionModel.of(items, linkTo(methodOn(MenuItemController.class)
                .getMenuItemsForRestaurant(restaurantName)).withSelfRel());
    }

    //get one by restaurant, RADI
    @GetMapping("/{restaurantName}")
    public EntityModel<MenuItem> getMenuItemForRestaurant(@PathVariable String restaurantName, @RequestBody MenuItem menuItem) {
        return helper.getMenuItem(restaurantName, menuItem);
    }


    //add, RADI
    @PostMapping("/{restaurantName}")
    public ResponseEntity<?> addMenuItem(@PathVariable String restaurantName, @RequestBody MenuItem menuItem) {
        var entityModel = helper.processAddRequest(restaurantName, menuItem);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    //RADI
    @PutMapping("/{restaurantName}")
    public ResponseEntity<?> updateMenuItem(@PathVariable String restaurantName, @RequestBody MenuItem menuItem) {
        var entityModel = helper.updateMenuItem(restaurantName, menuItem);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    //delete by id, RADI
    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> deleteMenuItemById(@PathVariable UUID itemId) {
        menuItemService.deleteMenuItemById(itemId);

        return ResponseEntity.noContent()
                .build();
    }

}
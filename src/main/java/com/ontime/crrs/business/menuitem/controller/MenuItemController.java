package com.ontime.crrs.business.menuitem.controller;

import com.ontime.crrs.business.mapper.menuitem.MenuItemMapper;
import com.ontime.crrs.business.menuitem.model.MenuItem;
import com.ontime.crrs.business.menuitem.model.MenuItemModelAssembler;
import com.ontime.crrs.persistence.menuitem.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menuitems")
public class MenuItemController {

    private final MenuItemService menuItemService;
    private final MenuItemModelAssembler modelAssembler;
    private final MenuItemMapper mapper;

    //get by name and restaurant
    @GetMapping("/{name}")
    public EntityModel<MenuItem> getMenuItemByRestaurant(@PathVariable String name) {
        var model = mapper.entityToModel(menuItemService.getMenuItemByName(name));

        return null;
    }


    //add
    @PostMapping("/{restaurantName}")
    public ResponseEntity<?> addNewMenuItem(@PathVariable String restaurantName, @RequestBody MenuItem menuItem) {
        var menuItemEntity = menuItemService.addNewMenuItem(mapper.modelToEntity(menuItem));

        return modelAssembler.toModel(apper.entityToModel(menuItemEntity));
    }

    //see all by restaurant

    //see by category
}
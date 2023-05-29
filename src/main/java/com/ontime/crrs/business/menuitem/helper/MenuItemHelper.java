package com.ontime.crrs.business.menuitem.helper;

import com.ontime.crrs.business.mapper.menuitem.MenuItemMapper;
import com.ontime.crrs.business.menuitem.model.MenuItem;
import com.ontime.crrs.business.menuitem.model.MenuItemModelAssembler;
import com.ontime.crrs.business.restaurant.model.RestaurantCreationRequest;
import com.ontime.crrs.persistence.menuitem.service.MenuItemService;
import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import com.ontime.crrs.persistence.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Component
@RequiredArgsConstructor
public class MenuItemHelper {

    private final RestaurantService restaurantService;
    private final MenuItemMapper mapper;
    private final MenuItemModelAssembler modelAssembler;
    private final MenuItemService menuItemService;

    public RestaurantEntity confirmRestaurantExists(String restaurantName) {
        return restaurantService.findRestaurantByName(restaurantName);
    }

    public EntityModel<MenuItem> processAddRequest(String restaurantName, MenuItem menuItem) {
        var restaurant = confirmRestaurantExists(restaurantName);

        var menuItemEntity = mapper.modelToEntity(menuItem);
        menuItemEntity.setRestaurant(restaurant);

        return modelAssembler.toModel(mapper.entityToModel(menuItemService.addNewMenuItem(menuItemEntity)));
    }

    public EntityModel<MenuItem> getMenuItem(String restaurantName, String name) {
        var restaurant = confirmRestaurantExists(restaurantName);
        var menuItemEntity = menuItemService.getMenuItemByNameAndRestaurant(name, restaurant.getName());

        return modelAssembler.toModel(mapper.entityToModel(menuItemEntity));
    }

    public EntityModel<MenuItem> updateMenuItem(String restaurantName, MenuItem updatedMenuItem) {
        var oldMenuItem = menuItemService.getMenuItemByNameAndRestaurant(updatedMenuItem.getName(), restaurantName);

        copyProperties(updatedMenuItem, oldMenuItem, "name");

        menuItemService.addNewMenuItem(oldMenuItem);

        return modelAssembler.toModel(mapper.entityToModel(oldMenuItem));
    }

    public List<MenuItem> addMenuItems(RestaurantCreationRequest creationRequest) {
        var menuItemEntities = mapper.modelsToEntities(creationRequest.getMenuItems());
        var restaurantEntity = restaurantService.findRestaurantByName(creationRequest.getName());

        var addedMenuItems = menuItemEntities.stream()
                .peek(menuItemEntity -> menuItemEntity.setRestaurant(restaurantEntity))
                .map(menuItemService::addNewMenuItem)
                .toList();

        return mapper.entitiesToModels(addedMenuItems);
    }

}
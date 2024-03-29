package com.ontime.crrs.business.restaurant.helper;

import com.ontime.crrs.business.mapper.menuitem.MenuItemMapper;
import com.ontime.crrs.business.mapper.restaurant.RestaurantMapper;
import com.ontime.crrs.business.menuitem.helper.MenuItemHelper;
import com.ontime.crrs.business.restaurant.model.Restaurant;
import com.ontime.crrs.business.restaurant.model.RestaurantCreationRequest;
import com.ontime.crrs.business.restaurant.model.RestaurantCreationResponse;
import com.ontime.crrs.business.restaurant.model.RestaurantInformation;
import com.ontime.crrs.business.security.auth.service.AuthenticationService;
import com.ontime.crrs.business.table.helper.TableHelper;
import com.ontime.crrs.persistence.menuitem.service.MenuItemService;
import com.ontime.crrs.persistence.restaurant.service.RestaurantService;
import com.ontime.crrs.persistence.user.entity.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@RequiredArgsConstructor
public class RestaurantHelper {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;
    private final MenuItemHelper menuItemHelper;
    private final TableHelper tableHelper;
    private final AuthenticationService authService;
    private final MenuItemService menuItemService;
    private final MenuItemMapper menuItemMapper;

    public Restaurant updateRestaurant(HttpServletRequest request, Restaurant restaurantModel) {
        var owner = authService.getUserByToken(request);
        validateUserIsOwner(owner);

        var restaurantEntity = restaurantService.findRestaurantByOwner(owner.getEmail());

        copyProperties(restaurantModel, restaurantEntity, "owner");
        copyProperties(restaurantModel.getLocation(), restaurantEntity.getLocation());
        copyProperties(restaurantModel.getWorkingHours(), restaurantEntity.getWorkingHours());

        var savedEntity = restaurantService.updateRestaurant(restaurantEntity);

        return restaurantMapper.entityToModel(savedEntity);
    }

    public void processRestaurantDeletion(HttpServletRequest request) {
        var owner = authService.getUserByToken(request);
        validateUserIsOwner(owner);

        var restaurantEntity = restaurantService.findRestaurantByOwner(owner.getEmail());

        restaurantService.deleteRestaurantById(restaurantEntity.getId());
    }

    public RestaurantCreationResponse saveRestaurant(HttpServletRequest request, RestaurantCreationRequest creationRequest) {
        var owner = authService.getUserByToken(request);
        validateUserIsOwner(owner);

        var restaurant = getRestaurantFromRequest(creationRequest);

        var entity = restaurantMapper.modelToEntity(restaurant);
        entity.setOwner(owner);

        var savedRestaurant = restaurantService.updateRestaurant(entity);

        var tables = tableHelper.addTables(creationRequest);
        var menuItems = menuItemHelper.addMenuItems(creationRequest);

        return RestaurantCreationResponse.builder()
                .restaurant(restaurantMapper.entityToModel(savedRestaurant))
                .tables(tables)
                .menuItems(menuItems)
                .build();
    }

    private Restaurant getRestaurantFromRequest(RestaurantCreationRequest creationRequest) {
        return Restaurant.builder()
                .name(creationRequest.getName())
                .location(creationRequest.getLocation())
                .image(creationRequest.getImage())
                .description(creationRequest.getDescription())
                .phoneNumber(creationRequest.getPhoneNumber())
                .workingHours(creationRequest.getWorkingHours())
                .build();
    }

    private void validateUserIsOwner(UserEntity user) {
        if (!user.getRole().name().equals("OWNER")) {
            throw new RuntimeException("User that is not OWNER cannot modify a restaurant.");
        }
    }

    public RestaurantInformation getRestaurantInformation(String restaurantName) {
        var restaurant = restaurantMapper.entityToModel(restaurantService.findRestaurantByName(restaurantName));
        var menuItems = menuItemMapper.entitiesToModels(menuItemService.getMenuItemsForRestaurant(restaurantName));

        return RestaurantInformation.builder()
                .restaurant(restaurant)
                .menuItems(menuItems)
                .build();
    }

    public Restaurant getRestaurantByOwner(HttpServletRequest request) {
        var owner = authService.getUserByToken(request);
        validateUserIsOwner(owner);

        var restaurantEntity = restaurantService.findRestaurantByOwner(owner.getEmail());

        return restaurantMapper.entityToModel(restaurantEntity);
    }

}
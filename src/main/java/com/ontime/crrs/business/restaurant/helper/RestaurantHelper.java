package com.ontime.crrs.business.restaurant.helper;

import com.ontime.crrs.business.mapper.restaurant.RestaurantMapper;
import com.ontime.crrs.business.menuitem.helper.MenuItemHelper;
import com.ontime.crrs.business.restaurant.model.Restaurant;
import com.ontime.crrs.business.restaurant.model.RestaurantCreationRequest;
import com.ontime.crrs.business.restaurant.model.RestaurantCreationResponse;
import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import com.ontime.crrs.persistence.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@RequiredArgsConstructor
public class RestaurantHelper {

    private final RestaurantService restaurantService;
    private final MenuItemHelper menuItemHelper;
    private final RestaurantMapper restaurantMapper;

    public RestaurantEntity transferProperties(Restaurant restaurantModel, String name) {
        var updatedRestaurantEntity = restaurantService.findRestaurantByName(name);

        copyProperties(restaurantModel, updatedRestaurantEntity);
        copyProperties(restaurantModel.getLocation(), updatedRestaurantEntity.getLocation());

        restaurantService.updateRestaurant(updatedRestaurantEntity);

        return updatedRestaurantEntity;
    }

    public RestaurantCreationResponse processCreationRequest(RestaurantCreationRequest creationRequest) {
        var restaurant = getRestaurantFromRequest(creationRequest);

        var savedRestaurant = restaurantService.updateRestaurant(restaurantMapper.modelToEntity(restaurant));

        var menuItems = menuItemHelper.addMenuItems(creationRequest);

        return RestaurantCreationResponse.builder()
                .restaurant(restaurantMapper.entityToModel(savedRestaurant))
                .menuItems(menuItems)
                .build();
    }

    private Restaurant getRestaurantFromRequest(RestaurantCreationRequest creationRequest){
        return Restaurant.builder()
                .name(creationRequest.getName())
                .location(creationRequest.getLocation())
                .image(creationRequest.getImage())
                .description(creationRequest.getDescription())
                .phoneNumber(creationRequest.getPhoneNumber())
                .build();
    }

}
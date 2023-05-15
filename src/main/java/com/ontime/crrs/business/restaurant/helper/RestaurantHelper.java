package com.ontime.crrs.business.restaurant.helper;

import com.ontime.crrs.business.mapper.restaurant.RestaurantMapper;
import com.ontime.crrs.business.mapper.table.TableMapper;
import com.ontime.crrs.business.restaurant.model.Restaurant;
import com.ontime.crrs.business.restaurant.model.RestaurantCreationRequest;
import com.ontime.crrs.business.restaurant.model.RestaurantInformation;
import com.ontime.crrs.business.security.auth.service.AuthenticationService;
import com.ontime.crrs.business.table.helper.TableHelper;
import com.ontime.crrs.persistence.restaurant.service.RestaurantService;
import com.ontime.crrs.persistence.table.service.TableService;
import com.ontime.crrs.persistence.user.entity.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@RequiredArgsConstructor
public class RestaurantHelper {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;
    private final TableHelper tableHelper;
    private final TableMapper tableMapper;
    private final TableService tableService;
    private final AuthenticationService authService;

    public Restaurant updateRestaurant(HttpServletRequest request, Restaurant restaurantModel) {
        var owner = authService.getUserByToken(request);
        validateUserIsOwner(owner);

        var restaurantEntity = restaurantService.findRestaurantByOwner(owner.getEmail());

        copyProperties(restaurantModel, restaurantEntity);
        copyProperties(restaurantModel.getLocation(), restaurantEntity.getLocation());
        //TODO: Add WorkingHours

        var savedEntity = restaurantService.updateRestaurant(restaurantEntity);

        return restaurantMapper.entityToModel(savedEntity);
    }

    public UUID processRestaurantDeletion(HttpServletRequest request) {
        var owner = authService.getUserByToken(request);
        validateUserIsOwner(owner);

        var restaurantEntity = restaurantService.findRestaurantByOwner(owner.getEmail());

        return restaurantEntity.getId();
    }

    public RestaurantInformation saveRestaurant(HttpServletRequest request, RestaurantCreationRequest creationRequest) {
        var owner = authService.getUserByToken(request);
        validateUserIsOwner(owner);

        var restaurant = getRestaurantFromRequest(creationRequest);

        var entity = restaurantMapper.modelToEntity(restaurant);
        entity.setOwner(owner);

        var savedRestaurant = restaurantService.updateRestaurant(entity);

        var tables = tableHelper.addTables(creationRequest);

        return RestaurantInformation.builder()
                .restaurant(restaurantMapper.entityToModel(savedRestaurant))
                .tables(tables)
                .build();
    }

    private Restaurant getRestaurantFromRequest(RestaurantCreationRequest creationRequest) {
        return Restaurant.builder()
                .name(creationRequest.getName())
                .location(creationRequest.getLocation())
                .image(creationRequest.getImage())
                .description(creationRequest.getDescription())
                .phoneNumber(creationRequest.getPhoneNumber())
                //TODO: Add WorkingHours
                .build();
    }

    private void validateUserIsOwner(UserEntity user) {
        if (!user.getRole().name().equals("OWNER")) {
            throw new RuntimeException("User that is not OWNER cannot modify a restaurant.");
        }
    }

    public RestaurantInformation mergeRestaurantInformation(String restaurantName) {
        var restaurantEntity = restaurantService.findRestaurantByName(restaurantName);
        var tableEntities = tableService.findTablesByRestaurant(restaurantName);
        //TODO: add menu items

        return RestaurantInformation.builder()
                .restaurant(restaurantMapper.entityToModel(restaurantEntity))
                .tables(tableMapper.entitiesToModels(tableEntities))
                .build();
    }

}
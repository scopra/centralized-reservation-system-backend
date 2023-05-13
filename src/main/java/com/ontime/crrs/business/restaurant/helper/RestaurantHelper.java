package com.ontime.crrs.business.restaurant.helper;

import com.ontime.crrs.business.mapper.restaurant.RestaurantMapper;
import com.ontime.crrs.business.restaurant.model.Restaurant;
import com.ontime.crrs.business.restaurant.model.RestaurantCreationRequest;
import com.ontime.crrs.business.restaurant.model.RestaurantCreationResponse;
import com.ontime.crrs.business.security.auth.AuthenticationService;
import com.ontime.crrs.business.security.jwt.JwtService;
import com.ontime.crrs.business.table.helper.TableHelper;
import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
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
    private final TableHelper tableHelper;
    private final JwtService jwtService;
    private final AuthenticationService authService;

    public RestaurantEntity updateRestaurant(HttpServletRequest request, Restaurant restaurantModel) {
        var owner = authService.getUserByToken(request);
        validateUserIsOwner(owner);

        var updatedRestaurantEntity = restaurantService.findRestaurantByOwner(owner.getEmail());

        copyProperties(restaurantModel, updatedRestaurantEntity);
        copyProperties(restaurantModel.getLocation(), updatedRestaurantEntity.getLocation());
        //TODO: Add WorkingHours

        restaurantService.updateRestaurant(updatedRestaurantEntity);

        return updatedRestaurantEntity;
    }

    public RestaurantCreationResponse saveRestaurant(RestaurantCreationRequest creationRequest) {
        var restaurant = getRestaurantFromRequest(creationRequest);

        var savedRestaurant = restaurantService.updateRestaurant(restaurantMapper.modelToEntity(restaurant));

        var tables = tableHelper.addTables(creationRequest);

        return RestaurantCreationResponse.builder()
                .restaurant(restaurantMapper.entityToModel(savedRestaurant))
                .tables(tables)
                .build();
    }

    public RestaurantCreationResponse saveRestaurant(HttpServletRequest request, RestaurantCreationRequest creationRequest) {
        var owner = authService.getUserByToken(request);
        validateUserIsOwner(owner);

        var restaurant = getRestaurantFromRequest(creationRequest);

        var entity = restaurantMapper.modelToEntity(restaurant);
        entity.setOwner(owner);

        var savedRestaurant = restaurantService.updateRestaurant(entity);

        var tables = tableHelper.addTables(creationRequest);

        return RestaurantCreationResponse.builder()
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

    private RestaurantEntity findRestaurantFromOwnerJWT(String ownerJWT) {
        return restaurantService.findRestaurantByOwner(jwtService.extractUsername(ownerJWT));
    }

    private void validateUserIsOwner(UserEntity user) {
        if (!user.getRole().name().equals("OWNER")) {
            throw new RuntimeException("User that is not OWNER cannot create a restaurant.");
        }
    }

}
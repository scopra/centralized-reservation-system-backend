package com.ontime.crrs.business.restaurant.processor;

import com.ontime.crrs.business.mapper.restaurant.RestaurantMapper;
import com.ontime.crrs.business.restaurant.model.Restaurant;
import com.ontime.crrs.business.security.auth.AuthenticationService;
import com.ontime.crrs.business.security.jwt.JwtService;
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
    private final JwtService jwtService;
    private final AuthenticationService authService;

    public RestaurantEntity updateRestaurant(HttpServletRequest request, Restaurant restaurantModel) {
        var owner = authService.getUserByToken(request);
        validateUserIsOwner(owner);

        var updatedRestaurantEntity = restaurantService.findRestaurantByOwner(owner.getEmail());

        copyProperties(restaurantModel, updatedRestaurantEntity);
        copyProperties(restaurantModel.getLocation(), updatedRestaurantEntity.getLocation());

        restaurantService.updateRestaurant(updatedRestaurantEntity);

        return updatedRestaurantEntity;
    }

    public Restaurant saveRestaurant(HttpServletRequest request, Restaurant restaurant) {
        var owner = authService.getUserByToken(request);
        validateUserIsOwner(owner);

        var entity = restaurantMapper.modelToEntity(restaurant);

        entity.setOwner(owner);

        restaurantService.updateRestaurant(entity);

        return restaurantMapper.entityToModel(entity);
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
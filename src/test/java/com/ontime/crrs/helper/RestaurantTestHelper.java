package com.ontime.crrs.helper;

import com.ontime.crrs.persistence.location.entity.LocationEntity;
import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import com.ontime.crrs.persistence.workinghours.entity.WorkingHoursEntity;
import lombok.experimental.Helper;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalTime;

@Component
public class RestaurantTestHelper {

    public static final String RESTAURANT_NAME = "Melody";
    public static final String RESTAURANT_ADDRESS = "Safeta Hadzica 8";
    public static final String RESTAURANT_MUNICIPALITY = "Novo Sarajevo";
    public static final String RESTAURANT_CITY = "Zenica";
    public static final String NAME_1 = "Name 1";
    public static final String NAME_2 = "Name 2";
    public static final String ADDRESS_1 = "Address 1";
    public static final String ADDRESS_2 = "Address 2";
    public static final LocalTime OPEN_TIME = LocalTime.of(8, 0, 0);
    public static final LocalTime CLOSE_TIME = LocalTime.of(16, 0, 0);
    public static final RestaurantEntity UPDATED_RESTAURANT = buildCustomRestaurantEntity(RESTAURANT_NAME, RESTAURANT_ADDRESS,
            RESTAURANT_MUNICIPALITY, RESTAURANT_CITY);
    public static final RestaurantEntity RESTAURANT_1 = buildCustomRestaurantEntity("First", "Address 1",
            RESTAURANT_MUNICIPALITY, RESTAURANT_CITY);
    public static final RestaurantEntity RESTAURANT_2 = buildCustomRestaurantEntity("Second", "Address 2",
            RESTAURANT_MUNICIPALITY, RESTAURANT_CITY);
    public static final RestaurantEntity EXISTING_RESTAURANT = preBuildRestaurantEntity();

    public static RestaurantEntity buildDefaultEntityWithName(String name) {
        return RestaurantEntity.builder()
                .name(name)
                .phoneNumber("033/123-456")
                .capacity(25)
                .description("Default entity description.")
                .location(buildDefaultLocation())
                .workingHours(buildDefaultWorkingHours())
                .build();
    }

    public static RestaurantEntity buildDefaultEntityWithAddress(String address) {
        return RestaurantEntity.builder()
                .name("McDonalds")
                .phoneNumber("033/123-456")
                .capacity(25)
                .description("Default entity description.")
                .location(buildDefaultLocation("Marsala Tita 36"))
                .workingHours(buildDefaultWorkingHours())
                .build();
    }

    private static LocationEntity buildDefaultLocation() {
        return LocationEntity.builder()
                .address("Marsala Tita 36")
                .municipality("Centar")
                .city("Sarajevo")
                .build();
    }

    private static LocationEntity buildDefaultLocation(String address) {
        return LocationEntity.builder()
                .address(address)
                .municipality("Centar")
                .city("Sarajevo")
                .build();
    }

    private static LocationEntity buildDefaultLocation(String address, String municipality, String city) {
        return LocationEntity.builder()
                .address(address)
                .municipality(municipality)
                .city(city)
                .build();
    }

    public static RestaurantEntity buildCustomRestaurantEntity(String name, String address, String municipality,
                                                               String city) {
        return RestaurantEntity.builder()
                .name(name)
                .description("Default description.")
                .capacity(25)
                .phoneNumber("033/123-456")
                .location(buildDefaultLocation(address, municipality, city))
                .workingHours(buildDefaultWorkingHours())
                .build();
    }

    public static RestaurantEntity preBuildRestaurantEntity() {
        return RestaurantEntity.builder()
                .name("Real")
                .phoneNumber("123-456-789")
                .capacity(27)
                .description("Old Description")
                .location(buildDefaultLocation())
                .workingHours(buildDefaultWorkingHours())
                .build();
    }

    public static WorkingHoursEntity buildDefaultWorkingHours() {
        return WorkingHoursEntity.builder()
                .openTime(Time.valueOf(OPEN_TIME))
                .closeTime(Time.valueOf(CLOSE_TIME))
                .build();
    }
}
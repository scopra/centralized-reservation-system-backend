package com.ontime.crrs.helper;

import com.ontime.crrs.business.restaurant.model.Restaurant;
import com.ontime.crrs.persistence.location.entity.LocationEntity;
import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import com.ontime.crrs.persistence.user.entity.UserEntity;
import com.ontime.crrs.persistence.workinghours.entity.WorkingHoursEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.ontime.crrs.persistence.user.util.Role.OWNER;

@Component
public class RestaurantTestHelper {

    public static final String RESTAURANT_NAME = "Melody";
    public static final String RESTAURANT_MUNICIPALITY = "Novo Sarajevo";
    public static final String RESTAURANT_CITY = "Zenica";
    public static final String NAME_1 = "Name 1";
    public static final String NAME_2 = "Name 2";
    public static final String ADDRESS_1 = "Address 1";
    public static final String ADDRESS_2 = "Address 2";
    public static final LocalTime OPEN_TIME = LocalTime.of(8, 0, 0);
    public static final LocalTime CLOSE_TIME = LocalTime.of(16, 0, 0);

    public static RestaurantEntity buildDefaultEntityWithName(String name, UserEntity owner) {
        return RestaurantEntity.builder()
                .name(name)
                .phoneNumber("033/123-456")
                .description("Default entity description.")
                .location(buildDefaultLocation())
                .workingHours(buildDefaultWorkingHours())
                .owner(owner)
                .build();
    }

    public static RestaurantEntity buildDefaultEntityWithAddress(String address, UserEntity owner) {
        return RestaurantEntity.builder()
                .name("McDonalds")
                .phoneNumber("033/123-456")
                .description("Default entity description.")
                .location(buildDefaultLocation("Marsala Tita 36"))
                .workingHours(buildDefaultWorkingHours())
                .owner(owner)
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
                                                               String city, UserEntity owner) {
        return RestaurantEntity.builder()
                .name(name)
                .description("Default description.")
                .phoneNumber("033/123-456")
                .location(buildDefaultLocation(address, municipality, city))
                .workingHours(buildDefaultWorkingHours())
                .owner(owner)
                .build();
    }

    public static RestaurantEntity preBuildRestaurantEntity(UserEntity owner) {
        return RestaurantEntity.builder()
                .name("KFC")
                .phoneNumber("123-456-789")
                .description("Old Description")
                .location(buildDefaultLocation())
                .workingHours(buildDefaultWorkingHours())
                .owner(owner)
                .build();
    }

    public static WorkingHoursEntity buildDefaultWorkingHours() {
        return WorkingHoursEntity.builder()
                .openTime(OPEN_TIME)
                .closeTime(CLOSE_TIME)
                .build();
    }

//TODO: Fix after Reservation is merged
//    public static Reservation getDefaultReservation(LocalTime start, LocalTime end, Restaurant restaurant) {
//        return Reservation.builder()
//                .capacity(5)
//                .date(LocalDate.of(2023, 5, 6))
//                .specialComment("Random comment")
//                .startTime(start)
//                .endTime(end)
//                .restaurant(restaurant)
//                .build();
//    }

    public static UserEntity buildDefaultOwner() {
        return UserEntity.builder()
                .email("owner@email.com")
                .role(OWNER)
                .name("Tony")
                .surname("Stark")
                .password("pisetinastolu123")
                .build();
    }

}
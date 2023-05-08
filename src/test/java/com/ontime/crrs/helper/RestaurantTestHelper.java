package com.ontime.crrs.helper;

import com.ontime.crrs.business.location.model.Location;
import com.ontime.crrs.business.reservation.model.Reservation;
import com.ontime.crrs.business.restaurant.model.Restaurant;
import com.ontime.crrs.business.workinghours.model.WorkingHours;
import com.ontime.crrs.persistence.location.entity.LocationEntity;
import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import com.ontime.crrs.persistence.workinghours.entity.WorkingHoursEntity;
import lombok.experimental.Helper;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalDate;
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
                .description("Default entity description.")
                .location(buildDefaultLocation())
                .workingHours(buildDefaultWorkingHours())
                .build();
    }

    public static RestaurantEntity buildDefaultEntityWithAddress(String address) {
        return RestaurantEntity.builder()
                .name("McDonalds")
                .phoneNumber("033/123-456")
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
                .phoneNumber("033/123-456")
                .location(buildDefaultLocation(address, municipality, city))
                .workingHours(buildDefaultWorkingHours())
                .build();
    }

    public static RestaurantEntity preBuildRestaurantEntity() {
        return RestaurantEntity.builder()
                .name("Real")
                .phoneNumber("123-456-789")
                .description("Old Description")
                .location(buildDefaultLocation())
                .workingHours(buildDefaultWorkingHours())
                .build();
    }

    public static Restaurant preBuildRestaurant(RestaurantEntity entity){
        return Restaurant.builder()
                .name(entity.getName())
                .image(entity.getImage())
                .workingHours(new WorkingHours(entity.getWorkingHours().getOpenTime(),
                        entity.getWorkingHours().getCloseTime()))
                .location(new Location(entity.getLocation().getAddress(), entity.getLocation().getMunicipality(),
                        entity.getLocation().getCity()))
                .description(entity.getDescription())
                .phoneNumber(entity.getPhoneNumber())
                .build();
    }

    public static WorkingHoursEntity buildDefaultWorkingHours() {
        return WorkingHoursEntity.builder()
                .openTime(OPEN_TIME)
                .closeTime(CLOSE_TIME)
                .build();
    }

    public static Reservation getDefaultReservation(LocalTime start, LocalTime end){
        return Reservation.builder()
                .capacity(5)
                .date(LocalDate.of(2023, 5, 6))
                .specialComment("Random comment")
                .startTime(start)
                .endTime(end)
                .restaurant(preBuildRestaurant(EXISTING_RESTAURANT))
                .build();
    }

}
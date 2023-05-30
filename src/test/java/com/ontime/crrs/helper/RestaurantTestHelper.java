package com.ontime.crrs.helper;

import com.ontime.crrs.business.reservation.model.Reservation;
import com.ontime.crrs.business.restaurant.model.Restaurant;
import com.ontime.crrs.business.table.model.Table;
import com.ontime.crrs.persistence.location.entity.LocationEntity;
import com.ontime.crrs.persistence.reservation.entity.ReservationEntity;
import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import com.ontime.crrs.persistence.table.entity.TableEntity;
import com.ontime.crrs.persistence.user.entity.UserEntity;
import com.ontime.crrs.persistence.workinghours.entity.WorkingHoursEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;

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
                .image("https://ibb.co/MsyQCtK")
                .location(buildDefaultLocation())
                .workingHours(buildDefaultWorkingHours())
                .owner(owner)
                .menuItems(Collections.emptyList())
                .rules(Collections.emptyList())
                .tables(Collections.emptyList())
                .reservations(Collections.emptyList())
                .build();
    }

    public static RestaurantEntity buildDefaultEntityWithAddress(String address, UserEntity owner) {
        return RestaurantEntity.builder()
                .name("McDonalds")
                .phoneNumber("033/123-456")
                .description("Default entity description.")
                .image("https://ibb.co/MsyQCtK")
                .location(buildDefaultLocation("Marsala Tita 36"))
                .workingHours(buildDefaultWorkingHours())
                .owner(owner)
                .menuItems(Collections.emptyList())
                .rules(Collections.emptyList())
                .tables(Collections.emptyList())
                .reservations(Collections.emptyList())
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
                .image("https://ibb.co/MsyQCtK")
                .location(buildDefaultLocation(address, municipality, city))
                .workingHours(buildDefaultWorkingHours())
                .owner(owner)
                .menuItems(Collections.emptyList())
                .rules(Collections.emptyList())
                .tables(Collections.emptyList())
                .reservations(Collections.emptyList())
                .build();
    }

    public static RestaurantEntity preBuildRestaurantEntity(UserEntity owner) {
        return RestaurantEntity.builder()
                .name("KFC")
                .phoneNumber("123-456-789")
                .description("Old Description")
                .image("https://ibb.co/MsyQCtK")
                .location(buildDefaultLocation())
                .workingHours(buildDefaultWorkingHours())
                .owner(owner)
                .menuItems(Collections.emptyList())
                .rules(Collections.emptyList())
                .tables(Collections.emptyList())
                .reservations(Collections.emptyList())
                .build();
    }

    public static WorkingHoursEntity buildDefaultWorkingHours() {
        return WorkingHoursEntity.builder()
                .openTime(OPEN_TIME)
                .closeTime(CLOSE_TIME)
                .build();
    }

    public static TableEntity getDefaultTable(RestaurantEntity restaurant) {
        return TableEntity.builder()
                .capacity(5)
                .restaurant(restaurant)
                .occupancies(Collections.emptyList())
                .reservations(Collections.emptyList())
                .build();
    }

    public static Reservation getReservationModel(ReservationEntity reservation) {
        return Reservation.builder()
                .reservationId(reservation.getReservationId())
                .table(Table.builder().capacity(reservation.getTable().getCapacity()).build())
                .restaurant(Restaurant.builder().name(reservation.getRestaurant().getName()).build())
                .startTime(reservation.getStartTime())
                .endTime(reservation.getEndTime())
                .date(reservation.getDate())
                .build();
    }

    //TODO: Fix after Reservation is merged
    public static ReservationEntity getDefaultReservation(LocalTime start, LocalTime end, RestaurantEntity restaurant, TableEntity table) {
        return ReservationEntity.builder()
                .numberOfGuests(5)
                .date(LocalDate.of(2023, 5, 6))
                .specialComment("Random comment")
                .startTime(start)
                .endTime(end)
                .restaurant(restaurant)
                .table(table)
                .build();
    }

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
package com.ontime.crrs.business.reservation.processor;

import com.ontime.crrs.business.mapper.restaurant.RestaurantMapper;
import com.ontime.crrs.business.mapper.user.UserMapper;
import com.ontime.crrs.business.price.PricingService;
import com.ontime.crrs.business.reservation.model.Reservation;
import com.ontime.crrs.business.reservation.model.ReservationCreationResponse;
import com.ontime.crrs.business.rules.discount.DiscountService;
import com.ontime.crrs.business.tableoccupancy.processor.TableOccupancyProcessor;
import com.ontime.crrs.business.workinghours.processor.WorkingHoursProcessor;
import com.ontime.crrs.persistence.reservation.entity.ReservationEntity;
import com.ontime.crrs.persistence.reservation.service.ReservationService;
import com.ontime.crrs.persistence.restaurant.service.RestaurantService;
import com.ontime.crrs.persistence.table.service.TableService;
import com.ontime.crrs.persistence.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationProcessor {

    private final RestaurantService restaurantService;
    private final WorkingHoursProcessor workingHoursProcessor;
    private final TableOccupancyProcessor tableOccupancyProcessor;
    private final DiscountService discountService;
    private final PricingService pricingService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final RestaurantMapper restaurantMapper;
    private final TableService tableService;
    private final ReservationService reservationService;

    public ReservationCreationResponse processReservation(Reservation reservation) {
        var restaurant = restaurantMapper
                .entityToModel(restaurantService.findRestaurantByName(reservation.getRestaurant().getName()));
        var customer = userMapper
                .entityToModel(userService.loadUserByEmail(reservation.getUser().getEmail()));

        workingHoursProcessor.isDuringWorkingHours(reservation);

        var assignedTable = tableOccupancyProcessor.assignTable(reservation);
        reservation.setTable(assignedTable);

        var discount = discountService.getDiscount(reservation);

        var priceBeforeDiscount = pricingService.getRegularPrice(reservation);
        var priceAfterDiscount = pricingService.getDiscountedPrice(reservation, discount);

        saveReservation(reservation);

        return ReservationCreationResponse.builder()
                .customer(customer)
                .table(assignedTable)
                .discount(discount)
                .restaurant(restaurant)
                .priceBeforeDiscount(priceBeforeDiscount)
                .priceAfterDiscount(priceAfterDiscount)
                .build();
    }

    private void saveReservation(Reservation reservation) {
        var tableEntity = tableService.findTableById(reservation.getTable().getId());
        var restaurantEntity = restaurantService.findRestaurantByName(reservation.getRestaurant().getName());
        var userEntity = userService.loadUserByEmail(reservation.getUser().getEmail());

        var reservationEntity = ReservationEntity.builder()
                .date(reservation.getDate())
                .startTime(reservation.getStartTime())
                .endTime(reservation.getEndTime())
                .numberOfGuests(reservation.getNumberOfGuests())
                .specialComment(reservation.getSpecialComment())
                .user(userEntity)
                .table(tableEntity)
                .restaurant(restaurantEntity)
                .build();

        reservationService.createReservation(reservationEntity);
    }

}
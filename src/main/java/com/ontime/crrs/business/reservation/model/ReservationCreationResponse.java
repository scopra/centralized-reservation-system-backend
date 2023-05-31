package com.ontime.crrs.business.reservation.model;

import com.ontime.crrs.business.restaurant.model.Restaurant;
import com.ontime.crrs.business.rules.order.OrderDiscount;
import com.ontime.crrs.business.table.model.Table;
import com.ontime.crrs.business.user.model.User;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
public class ReservationCreationResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID reservationId;
    private User customer;
    private Table table;
    private OrderDiscount discount;
    private Restaurant restaurant;
    private double priceBeforeDiscount;
    private double priceAfterDiscount;
    private boolean deleted = false;

}
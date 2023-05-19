package com.ontime.crrs.business.price;

import com.ontime.crrs.business.reservation.model.Reservation;
import com.ontime.crrs.business.rules.order.OrderDiscount;
import org.springframework.stereotype.Service;

@Service
public interface PricingService {

    public double getDiscountedPrice(Reservation reservation, OrderDiscount orderDiscount);

    public double getRegularPrice(Reservation reservation);

}
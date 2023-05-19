package com.ontime.crrs.business.rules.discount;

import com.ontime.crrs.business.reservation.model.Reservation;
import com.ontime.crrs.business.rules.order.OrderDiscount;
import org.springframework.stereotype.Service;

@Service
public interface DiscountService {

    public OrderDiscount getDiscount(Reservation reservation);

}
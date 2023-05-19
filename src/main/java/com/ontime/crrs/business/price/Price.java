package com.ontime.crrs.business.price;

import com.ontime.crrs.business.reservation.model.Reservation;
import com.ontime.crrs.business.rules.discount.DiscountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO: REMOVE (FOR TESTING PURPOSES)
@RestController
@RequestMapping("/price")
@RequiredArgsConstructor
public class Price {

    private final DiscountServiceImpl discountService;
    private final PricingService pricingService;

    @GetMapping
    public void getDiscountForReservation(@RequestBody Reservation reservation) {
        var orderDiscounts = discountService.getDiscount(reservation);

        System.out.println("Order discount map: \n" + orderDiscounts.toString());

        var discountedPrice = pricingService.getDiscountedPrice(reservation, orderDiscounts);
        var regularPrice = pricingService.getRegularPrice(reservation);

        System.out.println("Discounted price: " + discountedPrice + "\nRegular price: " + regularPrice);
    }

}
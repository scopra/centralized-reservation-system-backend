package com.ontime.crrs.business.price;

import com.ontime.crrs.business.menuitem.model.MenuItem;
import com.ontime.crrs.business.reservation.model.Reservation;
import com.ontime.crrs.business.rules.order.OrderDiscount;
import org.springframework.stereotype.Service;

import static com.ontime.crrs.persistence.menuitem.util.Category.ALL;

@Service
public class PricingServiceImpl implements PricingService {

    public double getDiscountedPrice(Reservation reservation, OrderDiscount orderDiscount) {
        var discountMap = orderDiscount.getItemDiscounts();

        int allDiscountPercentage = 0;
        if (discountMap.containsKey(ALL)) {
            allDiscountPercentage = discountMap.get(ALL);
        }

        return reservation.getMenuItems().stream()
                .mapToDouble(item -> {
                    double itemPrice = item.getPrice();
                    var itemCategory = item.getCategory();

                    if (discountMap.containsKey(itemCategory)) {
                        int discountPercentage = discountMap.get(itemCategory);
                        return itemPrice * (1 - (discountPercentage / 100.0));
                    } else {
                        return itemPrice;
                    }
                })
                .sum() * (1 - (allDiscountPercentage / 100.0));
    }

    public double getRegularPrice(Reservation reservation) {
        var menuItemList = reservation.getMenuItems();

        return menuItemList.stream()
                .mapToDouble(MenuItem::getPrice)
                .sum();
    }

}
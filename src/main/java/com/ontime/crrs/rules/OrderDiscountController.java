package com.ontime.crrs.rules;

import com.ontime.crrs.rules.discount.DiscountService;
import com.ontime.crrs.rules.order.OrderDiscount;
import com.ontime.crrs.rules.order.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderDiscountController {

    private final DiscountService discountService;
    private final MonitoringSystem monitoringSystem;

    @GetMapping("/random")
    public boolean getRandomValue() {
        return monitoringSystem.isDuringQuietTimes();
    }

    @PostMapping("/get-discount")
    public ResponseEntity<OrderDiscount> getDiscount(@RequestBody OrderRequest orderRequest) {
        OrderDiscount discount = discountService.getDiscount(orderRequest);
        return new ResponseEntity<>(discount, HttpStatus.OK);
    }

}
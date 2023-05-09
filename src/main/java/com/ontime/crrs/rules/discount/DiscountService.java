//package com.ontime.crrs.rules.discount;
//
//import com.ontime.crrs.rules.order.OrderDiscount;
//import com.ontime.crrs.rules.order.OrderRequest;
//import lombok.RequiredArgsConstructor;
//import org.kie.api.runtime.KieContainer;
//import org.kie.api.runtime.KieSession;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class DiscountService {
//
//    private final KieContainer kieContainer;
//
//    public OrderDiscount getDiscount(OrderRequest orderRequest) {
//        OrderDiscount orderDiscount = new OrderDiscount();
//        KieSession kieSession = kieContainer.newKieSession();
//
//        kieSession.setGlobal("orderDiscount", orderDiscount);
//        kieSession.insert(orderRequest);
//        kieSession.fireAllRules();
//        kieSession.dispose();
//
//        return orderDiscount;
//    }
//
//}
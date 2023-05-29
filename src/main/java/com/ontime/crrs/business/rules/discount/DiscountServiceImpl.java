package com.ontime.crrs.business.rules.discount;

import com.ontime.crrs.business.mapper.rule.RuleMapper;
import com.ontime.crrs.business.reservation.model.Reservation;
import com.ontime.crrs.business.rules.MonitoringSystem;
import com.ontime.crrs.business.rules.model.Rule;
import com.ontime.crrs.business.rules.order.OrderDiscount;
import com.ontime.crrs.persistence.rule.service.RuleService;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final KieContainer kieContainer;
    private final MonitoringSystem monitoringSystem;
    private final RuleService ruleService;
    private final RuleMapper ruleMapper;

    public OrderDiscount getDiscount(Reservation reservation) {
        KieSession kieSession = kieContainer.newKieSession();
        OrderDiscount orderDiscount = new OrderDiscount();
        orderDiscount.setItemDiscounts(new HashMap<>());

        monitoringSystem.setQuietTimes(monitoringSystem.isDuringQuietTimes());
        var ruleList = getRulesByRestaurant(reservation.getRestaurant().getName());
        var orderedItems = reservation.getMenuItems();

        kieSession.setGlobal("orderDiscount", orderDiscount);

        ruleList.forEach(kieSession::insert);
        orderedItems.forEach(kieSession::insert);
        kieSession.insert(monitoringSystem);
        kieSession.insert(reservation);

        kieSession.fireAllRules();
        kieSession.dispose();

        return orderDiscount;
    }

    private List<Rule> getRulesByRestaurant(String restaurantName) {
        return ruleMapper.entitiesToModels(ruleService.findRulesByRestaurant(restaurantName));
    }

}
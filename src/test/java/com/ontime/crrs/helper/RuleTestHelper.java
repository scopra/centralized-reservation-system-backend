package com.ontime.crrs.helper;

import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import com.ontime.crrs.persistence.rule.entity.RuleEntity;
import com.ontime.crrs.rules.util.RuleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RuleTestHelper {

    public static RuleEntity prebuildRule(RuleType ruleType, RestaurantEntity restaurant) {
        return RuleEntity.builder()
                .ruleType(ruleType)
                .discount(20)
                .end(LocalTime.MAX)
                .start(LocalTime.MIN)
                .groupSize(5)
                .restaurant(restaurant)
                .build();
    }

}
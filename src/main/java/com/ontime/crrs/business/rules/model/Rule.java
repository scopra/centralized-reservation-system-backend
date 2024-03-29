package com.ontime.crrs.business.rules.model;

import com.ontime.crrs.business.restaurant.model.Restaurant;
import com.ontime.crrs.business.rules.util.RuleType;
import com.ontime.crrs.persistence.menuitem.util.Category;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
@Component
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Rule implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;
    private RuleType ruleType;
    private int groupSize;
    private LocalTime startTime;
    private LocalTime endTime;
    private int discount;
    private Category discountOn;
    private Restaurant restaurant;
    private DayOfWeek dayOfWeek;

}
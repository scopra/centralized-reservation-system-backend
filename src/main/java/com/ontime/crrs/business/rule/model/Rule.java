package com.ontime.crrs.business.rule.model;

import com.ontime.crrs.business.restaurant.model.Restaurant;
import com.ontime.crrs.persistence.rule.util.RuleType;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
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
    private LocalTime start;
    private LocalTime end;
    private int discount;
    private Restaurant restaurant;

}
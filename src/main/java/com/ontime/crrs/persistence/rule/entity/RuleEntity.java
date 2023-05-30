package com.ontime.crrs.persistence.rule.entity;

import com.ontime.crrs.business.rules.util.RuleType;
import com.ontime.crrs.persistence.menuitem.util.Category;
import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rule")
public class RuleEntity {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Column(
            name = "rule_id",
            updatable = false,
            columnDefinition = "uuid"
    )
    private UUID id;

    @Column(
            name = "rule_type",
            nullable = false
    )
    private RuleType ruleType;

    @Column(name = "group_size")
    private int groupSize;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(
            name = "discount_on",
            nullable = false
    )
    private Category discountOn;

    @Column(
            name = "discount",
            nullable = false
    )
    private int discount;

    @Column(
            name = "day_of_week"
    )
    private DayOfWeek dayOfWeek;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RuleEntity that = (RuleEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
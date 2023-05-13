package com.ontime.crrs.persistence.rule.entity;

import com.ontime.crrs.persistence.menuitem.util.Category;
import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import com.ontime.crrs.rules.util.RuleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rule")
public class RuleEntity {

    @Id
    @GeneratedValue
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

    public void setRuleType(RuleType ruleType) {
        this.ruleType = ruleType;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }

    public void setDiscountOn(Category discountOn) {
        this.discountOn = discountOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RuleEntity that = (RuleEntity) o;
        return groupSize == that.groupSize && discount == that.discount &&
                Objects.equals(id, that.id) && ruleType == that.ruleType &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) && discountOn == that.discountOn && dayOfWeek == that.dayOfWeek &&
                Objects.equals(restaurant, that.restaurant);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
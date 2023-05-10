package com.ontime.crrs.persistence.rule.entity;

import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import com.ontime.crrs.persistence.rule.util.RuleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

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

    @Column(name = "start")
    private LocalTime start;

    @Column(name = "end")
    private LocalTime end;

    @Column(
            name = "discount",
            nullable = false
    )
    private int discount;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    public void setRuleType(RuleType ruleType) {
        this.ruleType = ruleType;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }

    public UUID getId() {
        return id;
    }

    public RuleType getRuleType() {
        return ruleType;
    }

    public Optional getGroupSize() {
        return Optional.ofNullable(groupSize);
    }

    public Optional getStart() {
        return Optional.ofNullable(start);
    }

    public Optional getEnd() {
        return Optional.ofNullable(end);
    }

    public int getDiscount() {
        return discount;
    }

    public RestaurantEntity getRestaurant() {
        return restaurant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RuleEntity that = (RuleEntity) o;
        return groupSize == that.groupSize && discount == that.discount &&
                Objects.equals(id, that.id) && ruleType == that.ruleType &&
                Objects.equals(start, that.start) &&
                Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
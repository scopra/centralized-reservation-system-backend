package com.ontime.crrs.persistence.workinghours.entity;

import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "working_hours")
@ToString(exclude = "restaurant")
public class WorkingHoursEntity {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Column(
            name = "working_hours_id",
            columnDefinition = "uuid"
    )
    private UUID id;

    @Column(
            name = "open_time"
    )
    private LocalTime openTime;

    @Column(
            name = "close_time"
    )
    private LocalTime closeTime;

    @OneToOne(mappedBy = "workingHours")
    private RestaurantEntity restaurant;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        WorkingHoursEntity that = (WorkingHoursEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
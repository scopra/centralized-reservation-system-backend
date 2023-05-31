package com.ontime.crrs.persistence.reservation.entity;

import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import com.ontime.crrs.persistence.table.entity.TableEntity;
import com.ontime.crrs.persistence.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"restaurant", "user", "table"})
@Table(name = "reservation")
public class ReservationEntity {

    @Id
    @GeneratedValue
    @Column(
            name = "reservation_id",
            updatable = false,
            columnDefinition = "uuid"
    )
    private UUID reservationId;

    @Column(
            name = "date",
            nullable = false
    )
    private LocalDate date;

    @Column(
            name = "start_time",
            nullable = false
    )
    private LocalTime startTime;

    @Column(
            name = "end_time",
            nullable = false
    )
    private LocalTime endTime;

    @Column(
            name = "number_of_guests",
            nullable = false
    )
    private int numberOfGuests;

    @Column(
            name = "special_comment"
    )
    private String specialComment;

    @ManyToOne
    @JoinColumn(
            name = "restaurant_id"
    )
    private RestaurantEntity restaurant;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private UserEntity user;

    @ManyToOne
    @JoinColumn(
            name = "table_id",
            nullable = false
    )
    private TableEntity table;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReservationEntity that = (ReservationEntity) o;
        return getReservationId() != null && Objects.equals(getReservationId(), that.getReservationId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
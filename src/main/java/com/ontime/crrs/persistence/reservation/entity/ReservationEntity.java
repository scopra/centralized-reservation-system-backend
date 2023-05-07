package com.ontime.crrs.persistence.reservation.entity;

import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import com.ontime.crrs.persistence.table.entity.TableEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"restaurant"})
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
            name = "time",
            nullable = false
    )
    private LocalTime time;

    @Column(
            name = "number_of_guests",
            nullable = false
    )
    private int numberOfGuests;
    @Column(
            name = "lenght",
            nullable = false
    )
    private int lenght;


    @Column(
            name = "description",
            nullable = false
    )
    private String description;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    @Column(
            name = "user_id",
            nullable = false
    )
    private UUID userId;
    //todo add length here,tablleid user id and relations

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }

    public void setLenght(int lenght) { this.lenght = lenght; }

    public ReservationEntity(LocalDate date, LocalTime time, String description, int numberOfGuests,
                             RestaurantEntity restaurant, int lenght, UUID userId) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.numberOfGuests = numberOfGuests;
        this.restaurant = restaurant;
        this.lenght = lenght;
        this.userId = userId;

    }

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
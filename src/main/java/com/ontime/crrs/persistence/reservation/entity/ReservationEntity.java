package com.ontime.crrs.persistence.reservation.entity;

import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import java.util.UUID;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="reservation")
public class ReservationEntity {

    @Id
    @GeneratedValue
    @Column(
            name = "reservation_id",
            updatable = false,
            columnDefinition ="uuid"
    )
    private UUID id;

    @Column(
            name ="date",
            nullable=false
    )
    private LocalDate date;

    @Column(
            name ="time",
            nullable=false
    )
    private LocalTime time;

    @Column(
            name="number_of_guests",
            nullable=false
    )
    private int numberOfGuests;
    @Column(
            name ="description",
            nullable= false
    )
    private String description;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurantId;

    @Column(
            name = "user_Id",
            nullable= false
    )
    private UUID user_Id;

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

    public void setRestaurant_id(RestaurantEntity restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setUser_Id(UUID user_Id) {
        this.user_Id = user_Id;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReservationEntity that = (ReservationEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "ReservationEntity{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                ", numberOfGuests=" + numberOfGuests +
                ", description='" + description + '\'' +
                ", restaurant_id=" + restaurantId +
                ", user_Id=" + user_Id +
                '}';
    }
}

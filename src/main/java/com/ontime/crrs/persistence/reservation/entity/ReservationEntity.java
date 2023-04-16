package com.ontime.crrs.persistence.reservation.entity;

import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import jakarta.persistence.*;
import lombok.*;

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
    private UUID reservationId;

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
    private RestaurantEntity restaurant;

    @Column(
            name = "user_id",
            nullable= false
    )
    private UUID userId;

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

    public void setUserId(UUID userId) { this.userId = userId;
    }

    public ReservationEntity(LocalDate date,LocalTime time,String description,int numberOfGuests,UUID userId){
        this.date = date;
        this.time=time;
        this.description = description;
        this.numberOfGuests = numberOfGuests;
        this.userId = userId;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReservationEntity that)) return false;
        return getNumberOfGuests() == that.getNumberOfGuests() &&
                Objects.equals(getReservationId(), that.getReservationId()) &&
                Objects.equals(getDate(), that.getDate()) &&
                Objects.equals(getTime(), that.getTime()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getRestaurant(), that.getRestaurant()) &&
                Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReservationId(), getDate(), getTime(), getNumberOfGuests(), getDescription(), getRestaurant(), getUserId());
    }

    @Override
    public String toString() {
        return "ReservationEntity{" +
                "id=" + reservationId +
                ", date=" + date +
                ", time=" + time +
                ", numberOfGuests=" + numberOfGuests +
                ", description='" + description + '\'' +
                ", restaurant_id=" + restaurant +
                ", user_Id=" + userId +
                '}';
    }
}

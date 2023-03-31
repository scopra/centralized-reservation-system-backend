package com.ontime.crrs.persistence.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Entity
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "restaurant")
public class RestaurantEntity {

    @Id
    @GeneratedValue
    @Column(
            name = "restaurant_id",
            updatable = false,
            columnDefinition = "uuid"
    )
    private UUID id;

    @Column(
            name = "name",
            nullable = false,
            length = 20
    )
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "phone_number",
            nullable = false,
            length = 15
    )
    private String phoneNumber;

    @Column(
            name = "capacity",
            nullable = false
    )
    private int capacity;

    /*
        TODO:
            - add relationship to Location, Special Offer & Image
     */

    public RestaurantEntity(String name, String description, String phoneNumber, int capacity) {
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.capacity = capacity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
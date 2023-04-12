package com.ontime.crrs.persistence.restaurant.entity;

import com.ontime.crrs.persistence.location.entity.LocationEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Entity
@Builder
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
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
            unique = true,
            length = 20)
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
            - add relationship to Special Offer & Image
     */

    @OneToOne(
            cascade = CascadeType.ALL
            //targetEntity = LocationEntity.class
    )
    @JoinColumn(
            name = "location_id"
            //referencedColumnName = "id"
    )
    private LocationEntity location;

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

    public void setLocation(LocationEntity location) {
        this.location = location;
    }

    public RestaurantEntity(String name, String description, String phoneNumber, int capacity, LocationEntity location) {
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.capacity = capacity;
        this.location = location;
    }
}
package com.ontime.crrs.persistence.location.entity;

import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
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
@Table(name = "location")
public class LocationEntity {
    @Id
    @GeneratedValue
    @Column(
            name = "location_id",
            columnDefinition = "uuid"
    )
    private UUID id; //NOTE TO SELF: refresh DB schema

    @Column(
            name = "address",
            unique = true,
            length = 20
    )
    private String address;

    @Column(
            name = "municipality",
            length = 20
    )
    private String municipality;

    @Column(
            name = "city",
            length = 20
    )
    private String city;

    @OneToOne(mappedBy = "location")
    private RestaurantEntity restaurant;

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocationEntity(String address, String municipality, String city) {
        this.address = address;
        this.municipality = municipality;
        this.city = city;
    }
}
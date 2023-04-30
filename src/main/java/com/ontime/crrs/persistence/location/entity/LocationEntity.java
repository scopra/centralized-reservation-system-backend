package com.ontime.crrs.persistence.location.entity;

import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.UUID;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "location")
@ToString(exclude = "restaurant")
public class LocationEntity {

    @Id
    @GeneratedValue
    @Column(
            name = "location_id",
            columnDefinition = "uuid"
    )
    private UUID id;

    @Column(
            name = "address",
            unique = true,
            length = 35
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LocationEntity that = (LocationEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
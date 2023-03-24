package com.ontime.crrs.persistence.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@Table(name = "restaurant")
public class RestaurantEntity {

    @Id
    @SequenceGenerator(
            name = "restaurant_id_sequence",
            sequenceName = "restaurant_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "restaurant_id_sequence"
    )
    private Integer id;

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

}
package com.ontime.crrs.persistence.restaurant.entity;

import com.ontime.crrs.persistence.location.entity.LocationEntity;
import com.ontime.crrs.persistence.menuitem.entity.MenuItemEntity;
import com.ontime.crrs.persistence.rule.entity.RuleEntity;
import com.ontime.crrs.persistence.table.entity.TableEntity;
import com.ontime.crrs.persistence.user.entity.UserEntity;
import com.ontime.crrs.persistence.workinghours.entity.WorkingHoursEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurant")
@ToString(exclude = "location")
public class RestaurantEntity {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
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
            length = 20
    )
    private String name;

    @Column(name = "description")
    private String description;

    @Column(
            name = "phone_number",
            nullable = false,
            length = 15
    )
    private String phoneNumber;

    @Column(name = "image")
    private String image;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "location_id")
    private LocationEntity location;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner;

    @OneToMany(
            mappedBy = "restaurant",
            cascade = ALL,
            fetch = EAGER
    )
    private List<TableEntity> tables;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "working_hours_id")
    private WorkingHoursEntity workingHours;

    @OneToMany(
            mappedBy = "restaurant",
            cascade = ALL,
            fetch = EAGER
    )
    private List<MenuItemEntity> menuItems;

    @OneToMany(
            mappedBy = "restaurant",
            cascade = ALL,
            fetch = EAGER
    )
    private List<RuleEntity> rules;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantEntity that = (RestaurantEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
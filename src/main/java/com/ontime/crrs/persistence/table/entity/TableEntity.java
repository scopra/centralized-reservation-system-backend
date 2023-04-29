package com.ontime.crrs.persistence.table.entity;

import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import com.ontime.crrs.persistence.tableoccupancy.entity.TableOccupancyEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_table")
public class TableEntity {

    @Id
    @GeneratedValue
    @Column(
            name = "table_id",
            updatable = false,
            columnDefinition = "uuid"
    )
    private UUID id;

    @Column(
            name = "capacity",
            nullable = false
    )
    private int capacity;

    @OneToMany(
            mappedBy = "table",
            cascade = ALL
    )
    private List<TableOccupancyEntity> occupancies;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }

    public void setOccupancies(List<TableOccupancyEntity> occupancies) {
        this.occupancies = occupancies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableEntity that = (TableEntity) o;
        return capacity == that.capacity && Objects.equals(id, that.id) && Objects.equals(occupancies, that.occupancies)
                && Objects.equals(restaurant, that.restaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, capacity, occupancies, restaurant);
    }

}
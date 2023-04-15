package com.ontime.crrs.persistence.table.entity;
import com.ontime.crrs.persistence.location.entity.LocationEntity;
import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;
import java.util.UUID;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurantTable")
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

    @Column(
            name="occupancy_status",
            nullable = false
    )
    private boolean occupancyStatus;


    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    // Generate getters and setters for all the fields


    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setOccupancyStatus(boolean occupancyStatus) {
        this.occupancyStatus = occupancyStatus;
    }

    public TableEntity(int capacity, boolean occupancyStatus) {
        this.capacity = capacity;
        this.occupancyStatus = occupancyStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TableEntity that)) return false;
        return getCapacity() == that.getCapacity() &&
                isOccupancyStatus() == that.isOccupancyStatus() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getRestaurant(), that.getRestaurant());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCapacity(), isOccupancyStatus(), getRestaurant());
    }

    // Override toString method to print the object in a readable format
    @Override
    public String toString() {
        return "TableEntity{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", occupancyStatus=" + occupancyStatus +
                ", restaurant=" + restaurant +
                '}';
    }
}

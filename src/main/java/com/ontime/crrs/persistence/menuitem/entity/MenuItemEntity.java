package com.ontime.crrs.persistence.menuitem.entity;

import com.ontime.crrs.persistence.menuitem.util.Category;
import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu_item")
public class MenuItemEntity {

    @Id
    @GeneratedValue
    @Column(
            name = "menu_item_id",
            updatable = false,
            columnDefinition = "uuid"
    )
    private UUID id;

    @Column(
            name = "name",
            nullable = false,
            updatable = false,
            length = 50
    )
    private String name;

    @Column(
            name = "category",
            nullable = false,
            length = 10
    )
    private Category category;

    @Column(
            name = "price",
            nullable = false
    )
    private int price;

    @Column(
            name = "image",
            nullable = false
    )
    private String image;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItemEntity that = (MenuItemEntity) o;
        return price == that.price &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) && category == that.category &&
                Objects.equals(image, that.image) && Objects.equals(restaurant, that.restaurant);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
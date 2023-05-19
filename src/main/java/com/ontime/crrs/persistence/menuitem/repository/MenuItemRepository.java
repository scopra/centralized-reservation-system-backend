package com.ontime.crrs.persistence.menuitem.repository;

import com.ontime.crrs.persistence.menuitem.entity.MenuItemEntity;
import com.ontime.crrs.persistence.menuitem.util.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MenuItemRepository extends JpaRepository<MenuItemEntity, UUID> {

    List<MenuItemEntity> findMenuItemsByRestaurant_Name(String restaurantName);

    List<MenuItemEntity> findMenuItemsByCategoryAndRestaurant_Name(Category category, String restaurantName);

    Optional<MenuItemEntity> findMenuItemByNameAndRestaurant_Name(String name, String restaurantName);

}
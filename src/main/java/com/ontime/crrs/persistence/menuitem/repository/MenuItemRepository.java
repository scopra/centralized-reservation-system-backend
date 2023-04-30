package com.ontime.crrs.persistence.menuitem.repository;

import com.ontime.crrs.persistence.menuitem.entity.MenuItemEntity;
import com.ontime.crrs.persistence.menuitem.util.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MenuItemRepository extends JpaRepository<MenuItemEntity, UUID> {

    List<MenuItemEntity> findMenuItemByRestaurant_Name(String restaurantName);

    List<MenuItemEntity> findMenuItemsByCategory(Category category);

}
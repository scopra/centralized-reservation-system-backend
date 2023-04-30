package com.ontime.crrs.persistence.menuitem.service;

import com.ontime.crrs.persistence.menuitem.entity.MenuItemEntity;
import com.ontime.crrs.persistence.menuitem.util.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuItemService {

    MenuItemEntity addNewMenuItem(MenuItemEntity menuItemEntity);

    List<MenuItemEntity> getMenuItemsForRestaurant(String name);

    List<MenuItemEntity> getMenuItemsByCategory(Category category);

}
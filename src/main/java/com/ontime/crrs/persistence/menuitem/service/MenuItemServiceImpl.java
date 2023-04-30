package com.ontime.crrs.persistence.menuitem.service;

import com.ontime.crrs.persistence.menuitem.entity.MenuItemEntity;
import com.ontime.crrs.persistence.menuitem.repository.MenuItemRepository;
import com.ontime.crrs.persistence.menuitem.util.Category;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository repository;

    public MenuItemEntity addNewMenuItem(MenuItemEntity menuItemEntity) {
        return repository.save(menuItemEntity);
    }

    public List<MenuItemEntity> getMenuItemsForRestaurant(String name) {
        return repository.findMenuItemByRestaurant_Name(name);
    }

    public List<MenuItemEntity> getMenuItemsByCategory(Category category) {
        return repository.findMenuItemsByCategory(category);
    }

    public MenuItemEntity getMenuItemByName(String name, String restaurantName) {
        return repository.findMenuItemByNameAndRestaurant_Name(name, restaurantName)
                .orElseThrow(() -> new EntityNotFoundException("Menu item with name " +
                        name + " not found."));
    }

}
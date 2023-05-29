package com.ontime.crrs.persistence.menuitem.service;

import com.ontime.crrs.persistence.menuitem.entity.MenuItemEntity;
import com.ontime.crrs.persistence.menuitem.repository.MenuItemRepository;
import com.ontime.crrs.persistence.menuitem.util.Category;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository repository;

    public MenuItemEntity addNewMenuItem(MenuItemEntity menuItemEntity) {
        return repository.save(menuItemEntity);
    }

    public List<MenuItemEntity> getMenuItemsForRestaurant(String name) {
        return repository.findMenuItemsByRestaurant_Name(name);
    }

    public List<MenuItemEntity> getMenuItemsByCategoryAndRestaurantName(Category category, String restaurantName) {
        return repository.findMenuItemsByCategoryAndRestaurant_Name(category, restaurantName);
    }

    public MenuItemEntity getMenuItemByNameAndRestaurant(String menuItemName, String restaurantName) {
        return repository.findMenuItemByNameAndRestaurant_Name(menuItemName, restaurantName)
                .orElseThrow(() -> new EntityNotFoundException("Menu item with name " +
                        menuItemName + " not found."));
    }

    public boolean checkIfMenuItemExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Menu item with ID " + id + " does not exist.");
        }

        return true;
    }

    public void deleteMenuItemById(UUID id) {
        checkIfMenuItemExists(id);

        repository.deleteById(id);
    }

}
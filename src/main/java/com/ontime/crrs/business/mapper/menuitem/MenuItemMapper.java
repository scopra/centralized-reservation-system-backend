package com.ontime.crrs.business.mapper.menuitem;

import com.ontime.crrs.business.mapper.restaurant.RestaurantMapper;
import com.ontime.crrs.business.menuitem.model.MenuItem;
import com.ontime.crrs.persistence.menuitem.entity.MenuItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = RestaurantMapper.class, componentModel = "spring")
public interface MenuItemMapper {

    MenuItemMapper INSTANCE = Mappers.getMapper(MenuItemMapper.class);

    MenuItem entityToModel(MenuItemEntity entity);

    @Mapping(target = "id", ignore = true)
    MenuItemEntity modelToEntity(MenuItem model);

    List<MenuItem> entitiesToModels(List<MenuItemEntity> entities);

    List<MenuItemEntity> modelsToEntities(List<MenuItem> models);

}
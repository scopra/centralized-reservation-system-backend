package com.ontime.crrs.business.mapper.menuitem;

import com.ontime.crrs.business.mapper.restaurant.RestaurantMapper;
import org.mapstruct.Mapper;

@Mapper(uses = RestaurantMapper.class, componentModel = "spring")
public class MenuItemMapper {
}

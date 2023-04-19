package com.ontime.crrs.business.mapper.workinghours;

import com.ontime.crrs.business.mapper.restaurant.RestaurantMapper;
import com.ontime.crrs.business.workinghours.model.WorkingHours;
import com.ontime.crrs.persistence.workinghours.entity.WorkingHoursEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = RestaurantMapper.class, componentModel = "spring")
public interface WorkingHoursMapper {

    WorkingHoursMapper INSTANCE = Mappers.getMapper(WorkingHoursMapper.class);

    WorkingHours entityToModel(WorkingHoursEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    WorkingHoursEntity modelToEntity(WorkingHours model);

    List<WorkingHours> entitiesToModels(List<WorkingHoursEntity> entities);

    List<WorkingHoursEntity> modelsToEntities(List<WorkingHours> models);

}
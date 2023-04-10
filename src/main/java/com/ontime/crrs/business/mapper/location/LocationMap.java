package com.ontime.crrs.business.mapper.location;

import com.ontime.crrs.business.location.model.Location;
import com.ontime.crrs.persistence.location.entity.LocationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMap {

    LocationMap INSTANCE = Mappers.getMapper(LocationMap.class);

    Location entityToModel(LocationEntity entity);

    @Mapping(target = "id", ignore = true)
    LocationEntity modelToEntity(Location model);

    List<Location> entitiesToModels(List<LocationEntity> entities);

    List<LocationEntity> modelsToEntities(List<Location> models);
}
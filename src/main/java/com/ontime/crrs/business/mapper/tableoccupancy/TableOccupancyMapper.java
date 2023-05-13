package com.ontime.crrs.business.mapper.tableoccupancy;

import com.ontime.crrs.business.mapper.table.TableMapper;
import com.ontime.crrs.business.tableoccupancy.model.TableOccupancy;
import com.ontime.crrs.persistence.tableoccupancy.entity.TableOccupancyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        uses = TableMapper.class,
        componentModel = "spring"
)
public interface TableOccupancyMapper {

    //TODO: FIGURE OUT IF I NEED THIS

    TableMapper INSTANCE = Mappers.getMapper(TableMapper.class);

    TableOccupancy entityToModel(TableOccupancyEntity entity);

    @Mapping(target = "id", ignore = true)
    TableOccupancyEntity modelToEntity(TableOccupancy model);

    List<TableOccupancy> entitiesToModels(List<TableOccupancyEntity> entities);

    List<TableOccupancyEntity> modelsToEntities(List<TableOccupancy> models);

}
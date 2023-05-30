package com.ontime.crrs.business.mapper.table;

import com.ontime.crrs.business.table.model.Table;
import com.ontime.crrs.persistence.table.entity.TableEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TableMapper {

    TableMapper INSTANCE = Mappers.getMapper(TableMapper.class);

    Table entityToModel(TableEntity entity);

    TableEntity modelToEntity(Table model);

    List<Table> entitiesToModels(List<TableEntity> entities);

    List<TableEntity> modelsToEntities(List<Table> models);

}
package com.ontime.crrs.business.mapper;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EntityModelMapper<TEntity, TModel> {

    List<TEntity> mapListOfModelsToListOfEntities(List<TModel> models);

    List<TModel> mapListOfEntitiesToListOfModels(List<TEntity> entities);

    TEntity mapRestaurantModelToEntity(TModel model);

    TModel mapRestaurantEntityToModel(TEntity entity);
}

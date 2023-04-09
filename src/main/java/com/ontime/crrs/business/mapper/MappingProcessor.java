package com.ontime.crrs.business.mapper;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;

@Component
public abstract class MappingProcessor<TEntity, TModel> {

    public List<TEntity> mapListOfModelsToListOfEntities(List<TModel> models) {
        return models.stream()
                .map(this::mapModelToEntity)
                .collect(Collectors.toList());
    }

    public List<TModel> mapListOfEntitiesToListOfModels(List<TEntity> entities) {
        return entities.stream()
                .map(this::mapEntityToModel)
                .collect(Collectors.toList());
    }

    public TEntity mapModelToEntity(TModel model) {
        var entity = createEntityInstance();

        copyProperties(model, entity);

        return entity;

    }

    public TModel mapEntityToModel(TEntity entity) {
        var model = createModelInstance();

        copyProperties(entity, model, "id");

        return model;
    }

    protected abstract TEntity createEntityInstance();

    protected abstract TModel createModelInstance();
}
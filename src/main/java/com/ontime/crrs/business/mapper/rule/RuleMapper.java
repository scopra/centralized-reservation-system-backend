package com.ontime.crrs.business.mapper.rule;

import com.ontime.crrs.business.mapper.restaurant.RestaurantMapper;
import com.ontime.crrs.persistence.rule.entity.RuleEntity;
import com.ontime.crrs.business.rules.model.Rule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        uses = RestaurantMapper.class,
        componentModel = "spring"
)
public interface RuleMapper {

    RuleMapper INSTANCE = Mappers.getMapper(RuleMapper.class);

    Rule entityToModel(RuleEntity entity);

    RuleEntity modelToEntity(Rule model);

    List<Rule> entitiesToModels(List<RuleEntity> entities);

    List<RuleEntity> modelsToEntities(List<Rule> models);

}
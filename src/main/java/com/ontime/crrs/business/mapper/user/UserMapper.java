package com.ontime.crrs.business.mapper.user;

import com.ontime.crrs.business.user.model.User;
import com.ontime.crrs.persistence.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User entityToModel(UserEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    UserEntity modelToEntity(User model);

    List<User> entitiesToModels(List<UserEntity> entities);

    List<UserEntity> modelsToEntities(List<User> models);

}
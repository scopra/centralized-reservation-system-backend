package com.ontime.crrs.business.mapper.reservation;


import com.ontime.crrs.business.reservation.model.Reservation;
import com.ontime.crrs.persistence.reservation.entity.ReservationEntity;
import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper (componentModel = "spring")
public interface ReservationMapper {

    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    Reservation entityTomodel(ReservationEntity entity);

    @Mapping(target ="id",ignore = true)
    RestaurantEntity modelToEntity(Reservation model);

    List <Reservation> entitiesToModels(List<ReservationEntity> entities);
    List <ReservationEntity> modelToEntity(List<Reservation> models);


}

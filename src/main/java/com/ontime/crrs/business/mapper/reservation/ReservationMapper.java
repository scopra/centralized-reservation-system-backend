package com.ontime.crrs.business.mapper.reservation;
import org.mapstruct.factory.Mappers;


import com.ontime.crrs.business.reservation.model.Reservation;
import com.ontime.crrs.persistence.reservation.entity.ReservationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper ( componentModel = "spring")
public interface ReservationMapper {

    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);
    Reservation entityToModel(ReservationEntity entity);

    @Mapping(target = "reservationId", ignore = true)
    ReservationEntity modelToEntity(Reservation model);

    List <Reservation> entitiesToModels(List<ReservationEntity> entities);

    List <ReservationEntity> modelToEntity(List<Reservation> models);
}
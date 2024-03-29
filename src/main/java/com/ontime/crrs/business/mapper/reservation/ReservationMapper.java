package com.ontime.crrs.business.mapper.reservation;

import com.ontime.crrs.business.mapper.restaurant.RestaurantMapper;
import com.ontime.crrs.business.mapper.user.UserMapper;
import com.ontime.crrs.business.reservation.model.Reservation;
import com.ontime.crrs.persistence.reservation.entity.ReservationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        uses = {RestaurantMapper.class, UserMapper.class},
        componentModel = "spring"
)
public interface ReservationMapper {

    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    Reservation entityToModel(ReservationEntity entity);

    ReservationEntity modelToEntity(Reservation model);

    List<Reservation> entitiesToModels(List<ReservationEntity> entities);

    List<ReservationEntity> modelToEntity(List<Reservation> models);

}
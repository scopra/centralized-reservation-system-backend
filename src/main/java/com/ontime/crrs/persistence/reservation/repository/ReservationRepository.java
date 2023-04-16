package com.ontime.crrs.persistence.reservation.repository;

import com.ontime.crrs.persistence.reservation.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, UUID> {

    List<ReservationEntity> findByReservationId(UUID reservationId);

    List<ReservationEntity> findByDate(LocalDate date);

    List<ReservationEntity> findByRestaurantId(UUID restaurantId);

    List<ReservationEntity> findByUserId(UUID userId);


}
package com.ontime.crrs.persistence.reservation.repository;

import com.ontime.crrs.persistence.reservation.entity.ReservationEntity;
import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, UUID> {

    List<ReservationEntity> findByReservationId(UUID reservationId);

    List<ReservationEntity> findByDate(LocalDate date);
  //todo add it to service, pitaj nedima i tarika hocel trebati da se nadje rezervacija po datumu i imenu restorana


    List<ReservationEntity> findByUserId(UUID userId);


    List<ReservationEntity> findByRestaurant_Name(String restaurantName);
}
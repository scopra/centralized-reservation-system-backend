package com.ontime.crrs.persistence.reservation.repository;

import com.ontime.crrs.persistence.reservation.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, UUID> {

    List<ReservationEntity> findByReservationId(UUID reservationId);

    List<ReservationEntity> findByDate(LocalDate date);
    //todo add it to service, pitaj nedima i tarika hocel trebati da se nadje rezervacija po datumu i imenu restorana


    List<ReservationEntity> findByUserId(UUID userId);


    List<ReservationEntity> findByRestaurant_Name(String restaurantName);
}
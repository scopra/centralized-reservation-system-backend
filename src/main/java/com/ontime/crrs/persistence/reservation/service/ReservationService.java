package com.ontime.crrs.persistence.reservation.service;

import com.ontime.crrs.persistence.reservation.entity.ReservationEntity;
import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface ReservationService {
    ReservationEntity createReservation(ReservationEntity reservation);

    ReservationEntity updateReservation(ReservationEntity reservation);


    ReservationEntity findReservationById(UUID reservationId);

    List<ReservationEntity> findAllReservations();

    List<ReservationEntity> findReservationsByDate(LocalDate date);

    List<ReservationEntity> findReservationsByUserId(UUID userId);

    boolean checkIfReservationExistsById(UUID id);

    void cancelReservationById(UUID id);

    void cancelAllReservations();


}

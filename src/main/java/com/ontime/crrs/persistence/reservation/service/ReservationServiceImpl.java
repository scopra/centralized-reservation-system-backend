package com.ontime.crrs.persistence.reservation.service;


import com.ontime.crrs.business.reservation.exeption.ReservationNotFoundException;
import com.ontime.crrs.persistence.reservation.entity.ReservationEntity;
import com.ontime.crrs.persistence.reservation.repository.ReservationRepository;
import com.ontime.crrs.persistence.restaurant.service.RestaurantService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository repository;
    private final RestaurantService restaurantService;

    public ReservationEntity createReservation(ReservationEntity reservation) {
        var restaurantEntity =
                restaurantService.findRestaurantByName(reservation.getRestaurant().getName());

        reservation.setRestaurant(restaurantEntity);

        return repository.save(reservation);
    }

    public ReservationEntity updateReservation(ReservationEntity reservation) {
        return repository.save(reservation);
    }

    public ReservationEntity findReservationById(UUID reservationId) {
        return repository.findById(reservationId).orElseThrow(() -> new ReservationNotFoundException(reservationId));
    }

    public List<ReservationEntity> findAllReservations() {
        return repository.findAll();
    }

    public List<ReservationEntity> findReservationsByDate(LocalDate date) {
        return repository.findByDate(date);
    }

    public List<ReservationEntity> findReservationsByUserId(UUID userId) {

        return repository.findByUserId(userId);
    }

    public boolean checkIfReservationExistsById(UUID reservationId) {
        var found = repository.existsById(reservationId);

        if (!found) {
            throw new ReservationNotFoundException(reservationId);
        }

        return true;
    }

    public void cancelReservationById(UUID reservationId) {
        checkIfReservationExistsById(reservationId);

        repository.deleteById(reservationId);
    }

    public void cancelAllReservations() {
        repository.deleteAll();
    }

}

package com.ontime.crrs.persistence.reservation.service;


import com.ontime.crrs.business.reservation.exeption.ReservationNotFoundException;
import com.ontime.crrs.persistence.reservation.entity.ReservationEntity;
import com.ontime.crrs.persistence.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository repository;

    public ReservationEntity createReservation(ReservationEntity reservation){
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

    public boolean checkIfReservationExistsById(UUID id) {
        var found = repository.existsById(id);

        if (!found) {
            throw new ReservationNotFoundException(id);
        }

        return true;
    }

    public void cancelReservationById(UUID id) {
        checkIfReservationExistsById(id);
        repository.deleteById(id);
    }

    public void cancelAllReservations() {
        repository.deleteAll();
    }

}

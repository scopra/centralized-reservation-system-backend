package com.ontime.crrs.business.reservation.controller;


import com.ontime.crrs.business.mapper.reservation.ReservationMapper;
import com.ontime.crrs.business.reservation.model.Reservation;
import com.ontime.crrs.persistence.reservation.entity.ReservationEntity;
import com.ontime.crrs.persistence.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        // create a new RestaurantEntity object

        var reservationEntity = reservationMapper.modelToEntity(reservation);
        // create a new ReservationEntity object with the RestaurantEntity object
        /*ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setDate(reservation.getDate());
        reservationEntity.setTime(reservation.getTime());
        reservationEntity.setDescription(reservation.getDescription());
        reservationEntity.setNumberOfGuests(reservation.getNumberOfGuests());
        reservationEntity.setUserId(reservation.getUserId());*/

        // save the ReservationEntity object
        var savedEntity = reservationService.createReservation(reservationEntity);

        // map the saved ReservationEntity object to a Reservation object and return it in the response
        var savedReservation = reservationMapper.entityToModel(savedEntity);
        return ResponseEntity.ok(savedReservation);
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable UUID reservationId) {
        var reservationEntity = reservationService.findReservationById(reservationId);
        var reservation = reservationMapper.entityToModel(reservationEntity);

        return ResponseEntity.ok(reservation);
    }

    @GetMapping("/test")
    public Reservation getRestaurantModel(@RequestBody Reservation reservation) {
        var mappedEntity = reservationMapper.modelToEntity(reservation);

        var mappedModel = reservationMapper.entityToModel(mappedEntity);

        return mappedModel;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        var reservationEntities = reservationService.findAllReservations();
        var reservations = reservationMapper.entitiesToModels(reservationEntities);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Reservation>> getReservationsByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<ReservationEntity> reservationEntities = reservationService.findReservationsByDate(date);
        List<Reservation> reservations = reservationMapper.entitiesToModels(reservationEntities);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reservation>> getReservationsByUserId(@PathVariable UUID userId) {
        List<ReservationEntity> reservationEntities = reservationService.findReservationsByUserId(userId);
        List<Reservation> reservations = reservationMapper.entitiesToModels(reservationEntities);
        return ResponseEntity.ok(reservations);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> cancelReservationById(@PathVariable UUID reservationId) {
        reservationService.cancelReservationById(reservationId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> cancelAllReservations() {
        reservationService.cancelAllReservations();
        return ResponseEntity.noContent().build();
    }


}

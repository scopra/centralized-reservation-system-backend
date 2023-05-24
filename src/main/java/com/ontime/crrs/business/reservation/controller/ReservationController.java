package com.ontime.crrs.business.reservation.controller;


import com.ontime.crrs.business.mapper.reservation.ReservationMapper;
import com.ontime.crrs.business.reservation.model.Reservation;
import com.ontime.crrs.business.reservation.model.ReservationCreationResponse;
import com.ontime.crrs.business.reservation.model.ReservationModelAssembler;
import com.ontime.crrs.business.reservation.processor.ReservationProcessor;
import com.ontime.crrs.persistence.reservation.service.ReservationService;
import com.ontime.crrs.persistence.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;
    private final ReservationModelAssembler modelAssembler;
    private final RestaurantService restaurantService;
    private final ReservationProcessor reservationProcessor;

    //add restaurant name to path da ne moramo slat sa modelom restoran objekat nek se sam nadje u bazi
  /*  @PostMapping("/{restaurantName}")
    public ResponseEntity<?> createReservation(@PathVariable String restaurantName,
                                               @RequestBody Reservation reservation) {
        var restaurantEntity = restaurantService.findRestaurantByName(restaurantName);

        var reservationEntity = reservationMapper.modelToEntity(reservation);
        reservationEntity.setRestaurant(restaurantEntity);

        var savedReservation = reservationMapper.entityToModel(reservationService.createReservation(reservationEntity));
        var entityModel = modelAssembler.toModel(savedReservation);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }*/
    @PostMapping("/post1")
    public ResponseEntity<ReservationCreationResponse> createReservation(@RequestBody Reservation reservation) {
        ReservationCreationResponse response = reservationProcessor.processReservation(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
  //RADI OVAJ
    @GetMapping("{reservationId}")
    public EntityModel<Reservation> getReservationById(@PathVariable UUID reservationId) {
        var reservationEntity = reservationService.findReservationById(reservationId);

        var reservation = reservationMapper.entityToModel(reservationEntity);

        return modelAssembler.toModel(reservation);
    }


    //ovo se moze ostavit za admina,RADI
    @GetMapping
    public CollectionModel<EntityModel<Reservation>> getAllReservations() {
        var reservations =
                reservationMapper.entitiesToModels(reservationService.findAllReservations()).stream()
                        .map(modelAssembler :: toModel)
                        .toList();

        return CollectionModel.of(reservations, linkTo(methodOn(ReservationController.class)
                .getAllReservations()).withSelfRel());
    }

    //RADI .
    @GetMapping("/date/{date}")
    public ResponseEntity<List<Reservation>> getReservationsByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        var reservationEntities = reservationService.findReservationsByDate(date);

        var reservations = reservationMapper.entitiesToModels(reservationEntities);

        return ResponseEntity.ok(reservations);
    }
    //RADI
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reservation>> getReservationsByUserId(@PathVariable UUID userId) {
        var reservationEntities = reservationService.findReservationsByUserId(userId);

        var reservations = reservationMapper.entitiesToModels(reservationEntities);

        return ResponseEntity.ok(reservations);
    }

    //RADI
    @DeleteMapping("/{reservationId}")
    public ResponseEntity<?> cancelReservationById(@PathVariable UUID reservationId) {
        reservationService.cancelReservationById(reservationId);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping
    public ResponseEntity<?> cancelAllReservations() {
        reservationService.cancelAllReservations();

        return ResponseEntity
                .noContent().
                build();
    }
    //RADI
    @GetMapping("/restaurant/{restaurantName}")
    public ResponseEntity<List<Reservation>> getReservationsByRestaurantName(@PathVariable String restaurantName) {
        var reservationEntities = reservationService.findReservationsByRestaurantName(restaurantName);

        var reservations = reservationMapper.entitiesToModels(reservationEntities);

        return ResponseEntity.ok(reservations);
    }


    //TESTING
    @GetMapping("/test1")
    public ResponseEntity<ReservationCreationResponse> makeAReservationTest(@RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationProcessor.processReservation(reservation));
    }

}
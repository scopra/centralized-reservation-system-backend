package com.ontime.crrs.business.reservation.controller;


import com.ontime.crrs.business.mapper.reservation.ReservationMapper;
import com.ontime.crrs.business.reservation.model.Reservation;
import com.ontime.crrs.business.reservation.model.ReservationModelAssembler;
import com.ontime.crrs.business.restaurant.controller.RestaurantController;
import com.ontime.crrs.business.restaurant.model.Restaurant;
import com.ontime.crrs.business.restaurant.model.RestaurantModelAssembler;
import com.ontime.crrs.persistence.reservation.entity.ReservationEntity;
import com.ontime.crrs.persistence.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;
    private final ReservationModelAssembler modelAssembler;

    private final ReservationMapper mapper;



    @GetMapping
    public CollectionModel<EntityModel<Reservation>> getReservations() {
        var reservations =
                mapper.entitiesToModels(reservationService.findAllReservations()).stream()
                        .map(modelAssembler::toModel)
                        .toList();

        return CollectionModel.of(reservations, linkTo(methodOn(RestaurantController.class)
                .getRestaurants()).withSelfRel());
    }


    @GetMapping("/{id}")
    public EntityModel<Reservation> getReservationById(@PathVariable UUID reservationId) {
        var reservationEntity = reservationService.findReservationById(reservationId);

        var reservationModel = mapper.entityToModel(reservationEntity);

        return modelAssembler.toModel(reservationModel);
    }

    @PostMapping
    public ResponseEntity<?> addReservation(@RequestBody Reservation reservation) {
        var reservationEntity = mapper.modelToEntity(reservation);

        reservationService.createReservation(reservationEntity);

        var entityModel = modelAssembler.toModel(reservation);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}

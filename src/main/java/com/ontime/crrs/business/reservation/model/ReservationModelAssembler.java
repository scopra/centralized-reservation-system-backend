package com.ontime.crrs.business.reservation.model;

import com.ontime.crrs.business.reservation.controller.ReservationController;
import io.micrometer.common.lang.NonNullApi;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@NonNullApi
@RequiredArgsConstructor
public class ReservationModelAssembler implements RepresentationModelAssembler<Reservation, EntityModel<Reservation>> {

    @Override
    public EntityModel<Reservation> toModel(Reservation reservation) {
        return EntityModel.of(reservation,
                linkTo(methodOn(ReservationController.class).getReservationById(reservation.getReservationId())).withSelfRel(),
                linkTo(methodOn(ReservationController.class).getAllReservations()).withRel("reservations"));
    }
}

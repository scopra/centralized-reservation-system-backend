package com.ontime.crrs.business.reservation.model;


import com.ontime.crrs.business.reservation.controller.ReservationController;
import io.micrometer.common.lang.NonNullApi;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@NonNullApi
@RequiredArgsConstructor
public class ReservationModelAssembler {
   /* public EntityModel<Reservation> toModel(Reservation reservation) {
        return EntityModel.of(reservation,
                linkTo(methodOn(ReservationController.class).getRestaurantIDByDescription(reservation.getDescription())).withSelfRel(),
                linkTo(methodOn(ReservationController.class).getAllReservations()).withRel("reservations"));
    }
*/
}

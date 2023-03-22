package com.ontime.crrs.persistence.restaurant.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@Component
@EqualsAndHashCode
@RequiredArgsConstructor
public class Restaurant implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private String phoneNumber;
    private int capacity;

}
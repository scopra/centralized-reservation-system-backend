package com.ontime.crrs.business.table.model;

import com.ontime.crrs.business.restaurant.model.Restaurant;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@Component
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Table implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;
    private int capacity;
    private Restaurant restaurant;

}
package com.ontime.crrs.business.table.model;
import com.ontime.crrs.business.restaurant.model.Restaurant;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@Component
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Table implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int capacity;

    private boolean occupancyStatus;

    private Restaurant restaurant;
}

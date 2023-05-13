package com.ontime.crrs.business.restaurant.model;

import com.ontime.crrs.business.table.model.Table;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@Component
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantCreationResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Restaurant restaurant;
    private List<Table> tables;
    //TODO: Add menu items.

}
package com.ontime.crrs.business.restaurant.model;

import com.ontime.crrs.business.location.model.Location;
import com.ontime.crrs.business.menuitem.model.MenuItem;
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
public class RestaurantCreationRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private String phoneNumber;
    private String image;
    private Location location;
    private List<MenuItem> menuItems;

}
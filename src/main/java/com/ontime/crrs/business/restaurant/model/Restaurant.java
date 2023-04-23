package com.ontime.crrs.business.restaurant.model;

import com.ontime.crrs.business.location.model.Location;
import com.ontime.crrs.business.workinghours.model.WorkingHours;
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
public class Restaurant implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private String phoneNumber;
    private Location location;
    private WorkingHours workingHours;

}
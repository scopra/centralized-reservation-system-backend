package com.ontime.crrs.business.menuitem.model;

import com.ontime.crrs.business.restaurant.model.Restaurant;
import com.ontime.crrs.persistence.menuitem.util.Category;
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
public class MenuItem implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private int price;
    private Category category;
    private String image;
    private Restaurant restaurant;

}
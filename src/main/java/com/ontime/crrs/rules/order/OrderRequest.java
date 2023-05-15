package com.ontime.crrs.rules.order;

import com.ontime.crrs.business.menuitem.model.MenuItem;
import com.ontime.crrs.rules.CustomerType;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@Component
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String customerNumber;
    private Integer age;
    private Integer amount;
    private CustomerType customerType;
    private List<MenuItem> menuItems;
    private LocalTime orderTime;

}
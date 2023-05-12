package com.ontime.crrs.rules.order;

import com.ontime.crrs.persistence.menuitem.util.Category;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

@Data
public class OrderDiscount implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer overallDiscount = 1;
    private Map<Category, Integer> itemDiscounts;

}
package com.ontime.crrs.business.tableoccupancy.model;

import com.ontime.crrs.business.table.model.Table;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@Component
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TableOccupancy implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private LocalDate reservationDate;
    private LocalTime reservedFrom;
    private LocalTime reservedTo;
    private Table table;

}
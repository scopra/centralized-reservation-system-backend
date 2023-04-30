package com.ontime.crrs.business.workinghours.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Time;

@Data
@Builder
@Component
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class WorkingHours implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Time openTime;
    private Time closeTime;

}
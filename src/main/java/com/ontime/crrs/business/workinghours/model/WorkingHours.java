package com.ontime.crrs.business.workinghours.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalTime;

@Data
@Builder
@Component
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class WorkingHours implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private LocalTime openTime;
    private LocalTime closeTime;

}
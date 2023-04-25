package com.ontime.crrs.business.location.model;

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
public class Location implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String address;
    private String municipality;
    private String city;

}
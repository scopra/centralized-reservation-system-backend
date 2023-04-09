package com.ontime.crrs.business.location.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

@Data
@Component
@EqualsAndHashCode
@NoArgsConstructor
public class Location implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String address;
    private String municipality;
    private String city;
}

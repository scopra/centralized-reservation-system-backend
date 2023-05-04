package com.ontime.crrs.business.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String surname;
    private String email;
    private String password;
    private String confirmPassword;

}
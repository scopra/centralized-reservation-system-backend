package com.ontime.crrs.business.security.auth;

import com.ontime.crrs.business.mapper.user.UserMapper;
import com.ontime.crrs.business.security.exception.PasswordMismatchException;
import com.ontime.crrs.business.security.jwt.JwtService;
import com.ontime.crrs.business.user.model.User;
import com.ontime.crrs.persistence.user.entity.UserEntity;
import com.ontime.crrs.persistence.user.service.UserService;
import com.ontime.crrs.persistence.user.util.Role;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService service;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final UserMapper mapper;

    public AuthenticationResponse register(RegistrationRequest user, Role role) {
        validatePasswordMatch(user.getPassword(), user.getConfirmPassword());

        var userEntity = UserEntity.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(role)
                .build();

        service.registerUser(userEntity);

        var jwtToken = jwtService.generateToken(userEntity);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void validatePasswordMatch(String password, String confirmedPassword) {
        if (!password.equals(confirmedPassword)) {
            throw new PasswordMismatchException();
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );

        var user = service.loadUserByEmail(authRequest.getEmail());

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public User getUserByToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        var authHeaderParts = authHeader.split(" ");
        var token = authHeaderParts[1];

        var userEntity = service.loadUserByEmail(jwtService.extractUsername(token));

        return mapper.entityToModel(userEntity);
    }

}
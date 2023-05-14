package com.ontime.crrs.business.security.auth.controller;

import com.ontime.crrs.business.mapper.user.UserMapper;
import com.ontime.crrs.business.security.auth.model.AuthenticationRequest;
import com.ontime.crrs.business.security.auth.model.AuthenticationResponse;
import com.ontime.crrs.business.security.auth.model.RegistrationRequest;
import com.ontime.crrs.business.security.auth.service.AuthenticationService;
import com.ontime.crrs.business.user.model.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ontime.crrs.persistence.user.util.Role.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    @PostMapping("/register/customer")
    public ResponseEntity<AuthenticationResponse> registerCustomer(@RequestBody RegistrationRequest user) {
        return ResponseEntity.ok(authenticationService.register(user, CUSTOMER));
    }

    @PostMapping("/register/owner")
    public ResponseEntity<AuthenticationResponse> registerOwner(@RequestBody RegistrationRequest user) {
        return ResponseEntity.ok(authenticationService.register(user, OWNER));
    }

    @PostMapping("/register/staff")
    public ResponseEntity<AuthenticationResponse> registerStaff(@RequestBody RegistrationRequest user) {
        return ResponseEntity.ok(authenticationService.register(user, STAFF));
    }

    @PostMapping("/register/admin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(@RequestBody RegistrationRequest user) {
        return ResponseEntity.ok(authenticationService.register(user, ADMIN));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authRequest));
    }

    @GetMapping
    public ResponseEntity<User> getUserByJWT(HttpServletRequest request) {
        var user = userMapper.entityToModel(authenticationService.getUserByToken(request));

        return ResponseEntity.ok(user);
    }

}
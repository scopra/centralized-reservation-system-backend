package com.ontime.crrs.business.security.auth;

import com.ontime.crrs.business.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ontime.crrs.persistence.user.util.Role.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register/customer")
    public ResponseEntity<AuthenticationResponse> registerCustomer(@RequestBody User user) {
        return ResponseEntity.ok(authenticationService.register(user, CUSTOMER));
    }

    @PostMapping("/register/owner")
    public ResponseEntity<AuthenticationResponse> registerOwner(@RequestBody User user) {
        return ResponseEntity.ok(authenticationService.register(user, OWNER));
    }

    @PostMapping("/register/staff")
    public ResponseEntity<AuthenticationResponse> registerStaff(@RequestBody User user) {
        return ResponseEntity.ok(authenticationService.register(user, STAFF));
    }

    @PostMapping("/register/admin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(@RequestBody User user) {
        return ResponseEntity.ok(authenticationService.register(user, ADMIN));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authRequest));
    }

}
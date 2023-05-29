package com.ontime.crrs.business.subscription.service;

import com.ontime.crrs.business.security.auth.service.AuthenticationService;
import com.ontime.crrs.persistence.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ontime.crrs.persistence.user.util.Role.OWNER;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final AuthenticationService authService;
    private final UserRepository userRepository;

    public void subscribe(HttpServletRequest request) {
        var user = authService.getUserByToken(request);

        user.setRole(OWNER);

        userRepository.save(user);
    }

}
package com.ontime.crrs.business.subscription.controller;

import com.ontime.crrs.business.subscription.service.SubscriptionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<?> subscribe(HttpServletRequest request) {
        subscriptionService.subscribe(request);

        return ResponseEntity.ok("Subscription successful.");
    }

}
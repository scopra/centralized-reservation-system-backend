package com.ontime.crrs.business.subscription;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface SubscriptionService {

    void subscribe(HttpServletRequest request);

}
package com.ontime.crrs.rules;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MonitoringSystem {

    public boolean isDuringQuietTimes() {
        Random randomizer = new Random();

        return randomizer.nextBoolean();
    }

}
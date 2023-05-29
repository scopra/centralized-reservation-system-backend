package com.ontime.crrs.business.rules;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Random;

@Data
@Component
public class MonitoringSystem {

    private boolean quietTimes;

    public boolean isDuringQuietTimes() {
        Random randomizer = new Random();

        return randomizer.nextBoolean();
    }

}
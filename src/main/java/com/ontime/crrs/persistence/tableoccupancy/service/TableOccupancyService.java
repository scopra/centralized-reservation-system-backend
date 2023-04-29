package com.ontime.crrs.persistence.tableoccupancy.service;

import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public interface TableOccupancyService {

    boolean tableOccupiedForTime(LocalTime time);

}
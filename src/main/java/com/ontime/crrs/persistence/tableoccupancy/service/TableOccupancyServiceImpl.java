package com.ontime.crrs.persistence.tableoccupancy.service;

import java.time.LocalTime;

public class TableOccupancyServiceImpl implements TableOccupancyService{

    @Override
    public boolean tableOccupiedForTime(LocalTime time) {
        return false;
    }

}
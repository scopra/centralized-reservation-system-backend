package com.ontime.crrs.business.tableoccupancy.processor;

import com.ontime.crrs.business.table.model.Table;
import com.ontime.crrs.business.tableoccupancy.model.TableOccupancy;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class TableOccupancyProcessor {

    public Table findFreeTable() {
        return null;
    }


    public boolean tableOccupiedForTime(LocalTime time, TableOccupancy tableOccupancy) {

        return time.isAfter(tableOccupancy.getReservedFrom()) && time.isBefore(tableOccupancy.getReservedTo());
    }

}
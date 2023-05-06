package com.ontime.crrs.business.tableoccupancy.processor;

import com.ontime.crrs.business.reservation.model.Reservation;
import com.ontime.crrs.business.table.model.Table;
import org.springframework.stereotype.Service;

@Service
public interface TableOccupancyProcessor {

    Table assignTable(String restaurantName, Reservation reservation);

}
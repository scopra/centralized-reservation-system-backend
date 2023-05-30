package com.ontime.crrs.business.workinghours.processor;

import com.ontime.crrs.business.reservation.model.Reservation;
import org.springframework.stereotype.Service;

@Service
public interface WorkingHoursProcessor {

    void isDuringWorkingHours(Reservation reservation);

}
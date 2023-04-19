package com.ontime.crrs.persistence.workinghours.service;

import com.ontime.crrs.persistence.workinghours.entity.WorkingHoursEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface WorkingHoursService {

    WorkingHoursEntity updateWorkingHours(String restaurantName, WorkingHoursEntity workingHours);

    WorkingHoursEntity findWorkingHoursById(UUID id);

    WorkingHoursEntity findWorkingHoursByRestaurant(String restaurantName);

    WorkingHoursEntity findWorkingHoursByRestaurantId(UUID id);

}
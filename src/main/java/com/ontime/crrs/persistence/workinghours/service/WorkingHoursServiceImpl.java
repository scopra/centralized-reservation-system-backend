package com.ontime.crrs.persistence.workinghours.service;

import com.ontime.crrs.business.workinghours.exception.WorkingHoursNotFoundException;
import com.ontime.crrs.persistence.restaurant.service.RestaurantService;
import com.ontime.crrs.persistence.workinghours.entity.WorkingHoursEntity;
import com.ontime.crrs.persistence.workinghours.repository.WorkingHoursRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkingHoursServiceImpl implements WorkingHoursService {

    private final WorkingHoursRepository workingHoursRepository;
    private final RestaurantService restaurantService;

    @Override
    public WorkingHoursEntity updateWorkingHours(String restaurantName, WorkingHoursEntity workingHoursEntity) {
        var restaurantEntity = restaurantService.findRestaurantByName(restaurantName);
        var existingWorkingHours = workingHoursRepository.findWorkingHoursByRestaurant_Name(restaurantName);

        if (existingWorkingHours.isEmpty()) {
            workingHoursEntity.setRestaurant(restaurantEntity);
            restaurantEntity.setWorkingHours(workingHoursEntity);

            return workingHoursRepository.save(workingHoursEntity);
        }


        var existingWorkingHoursId = restaurantEntity.getWorkingHours().getId();
        var newWH = workingHoursRepository.findById(existingWorkingHoursId).get();
        newWH.setOpenTime(workingHoursEntity.getOpenTime());
        newWH.setCloseTime(workingHoursEntity.getCloseTime());

        return workingHoursRepository.save(newWH);
    }

    @Override
    public WorkingHoursEntity findWorkingHoursById(UUID id) {
        return workingHoursRepository.findById(id)
                .orElseThrow(() -> new WorkingHoursNotFoundException("Working hours not found for ID " + id));
    }

    @Override
    public WorkingHoursEntity findWorkingHoursByRestaurant(String restaurantName) {
        return workingHoursRepository.findWorkingHoursByRestaurant_Name(restaurantName)
                .orElseThrow(() -> new WorkingHoursNotFoundException("Working hours not found for " + restaurantName));
    }

    @Override
    public WorkingHoursEntity findWorkingHoursByRestaurantId(UUID id) {
        return workingHoursRepository.findWorkingHoursByRestaurant_Id(id)
                .orElseThrow(() -> new WorkingHoursNotFoundException("Working hours not found for restaurant ID" + id));
    }

}
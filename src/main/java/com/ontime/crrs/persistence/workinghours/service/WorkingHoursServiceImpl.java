package com.ontime.crrs.persistence.workinghours.service;

import com.ontime.crrs.business.workinghours.exception.WorkingHoursNotFoundException;
import com.ontime.crrs.persistence.restaurant.entity.RestaurantEntity;
import com.ontime.crrs.persistence.restaurant.service.RestaurantService;
import com.ontime.crrs.persistence.workinghours.entity.WorkingHoursEntity;
import com.ontime.crrs.persistence.workinghours.repository.WorkingHoursRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.springframework.beans.BeanUtils.copyProperties;


@Service
@RequiredArgsConstructor
public class WorkingHoursServiceImpl implements WorkingHoursService {

    private final WorkingHoursRepository workingHoursRepository;
    private final RestaurantService restaurantService;

    @Override
    public WorkingHoursEntity updateWorkingHours(String restaurantName, WorkingHoursEntity newWorkingHours) {
        var restaurantEntity = restaurantService.findRestaurantByName(restaurantName);
        var existingWorkingHours = workingHoursRepository.findWorkingHoursByRestaurant_Name(restaurantName);

        return existingWorkingHours.map(oldWorkingHours -> updateWhenExistent(oldWorkingHours, newWorkingHours))
                .orElseGet(() -> updateWhenNonExistent(newWorkingHours, restaurantEntity));
    }

    private WorkingHoursEntity updateWhenNonExistent(WorkingHoursEntity newWorkingHours, RestaurantEntity restaurant) {
        newWorkingHours.setRestaurant(restaurant);
        restaurant.setWorkingHours(newWorkingHours);

        return workingHoursRepository.save(newWorkingHours);
    }

    private WorkingHoursEntity updateWhenExistent(WorkingHoursEntity existingEntity, WorkingHoursEntity newEntity) {

        existingEntity.setOpenTime(newEntity.getOpenTime());
        existingEntity.setCloseTime(newEntity.getCloseTime());

        return workingHoursRepository.save(existingEntity);
    }

    @Override
    public WorkingHoursEntity findWorkingHoursById(UUID id) {
        return workingHoursRepository.findById(id)
                .orElseThrow(() -> new WorkingHoursNotFoundException("Working hours not found for ID " + id));
    }

    @Override
    public WorkingHoursEntity findWorkingHoursByRestaurant(String restaurantName) {
        return workingHoursRepository.findWorkingHoursByRestaurant_Name(restaurantName)
                .orElseThrow(() -> new WorkingHoursNotFoundException("Working hours not found for restaurant name "
                        + restaurantName));
    }

    @Override
    public WorkingHoursEntity findWorkingHoursByRestaurantId(UUID id) {
        return workingHoursRepository.findWorkingHoursByRestaurant_Id(id)
                .orElseThrow(() -> new WorkingHoursNotFoundException("Working hours not found for restaurant ID" + id));
    }

}
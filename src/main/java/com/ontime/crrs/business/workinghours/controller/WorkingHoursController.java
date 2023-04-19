package com.ontime.crrs.business.workinghours.controller;

import com.ontime.crrs.business.mapper.workinghours.WorkingHoursMapper;
import com.ontime.crrs.business.workinghours.model.WorkingHours;
import com.ontime.crrs.persistence.workinghours.entity.WorkingHoursEntity;
import com.ontime.crrs.persistence.workinghours.service.WorkingHoursService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workinghours")
@RequiredArgsConstructor
public class WorkingHoursController {

    private final WorkingHoursService service;
    private final WorkingHoursMapper mapper;

    @PostMapping("/{restaurantName}")
    public WorkingHours addWorkingHoursForRestaurant(@PathVariable String restaurantName,
                                                           @RequestBody WorkingHours workingHours) {

        var workingHoursEntity = mapper.modelToEntity(workingHours);

        var model = mapper.entityToModel(service.updateWorkingHours(restaurantName, workingHoursEntity));

        return model;
    }

    @GetMapping("/test")
    public void mapperTest(@RequestBody WorkingHours workingHours) {

    }

}

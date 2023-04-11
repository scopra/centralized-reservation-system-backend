package com.ontime.crrs.business.location.controller;

import com.ontime.crrs.business.location.model.Location;
import com.ontime.crrs.business.mapper.location.LocationMapper;
import com.ontime.crrs.persistence.location.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;
    private final LocationMapper mapper;


    //RADI
    @PostMapping("/test")
    public Location createLocation(@RequestBody Location location) {
        var entity = mapper.modelToEntity(location);

        locationService.updateLocation(entity);

        return location;
    }

}

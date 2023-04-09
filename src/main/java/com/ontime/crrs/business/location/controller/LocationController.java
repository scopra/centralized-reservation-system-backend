package com.ontime.crrs.business.location.controller;

import com.ontime.crrs.business.location.model.Location;
import com.ontime.crrs.business.mapper.MappingProcessor;
import com.ontime.crrs.persistence.location.entity.LocationEntity;
import com.ontime.crrs.persistence.location.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;
    private final MappingProcessor<LocationEntity, Location> mapper;


    //RADI
    @PostMapping("/test")
    public Location createLocation(@RequestBody Location location){
        var entity = mapper.mapModelToEntity(location);

        locationService.updateLocation(entity);

        return location;
    }

}

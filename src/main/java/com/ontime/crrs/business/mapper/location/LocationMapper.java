package com.ontime.crrs.business.mapper.location;

import com.ontime.crrs.business.location.model.Location;
import com.ontime.crrs.business.mapper.MappingProcessor;
import com.ontime.crrs.persistence.location.entity.LocationEntity;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper extends MappingProcessor<LocationEntity, Location> {

    @Override
    protected LocationEntity createEntityInstance() {
        return new LocationEntity();
    }

    @Override
    protected Location createModelInstance() {
        return new Location();
    }
}
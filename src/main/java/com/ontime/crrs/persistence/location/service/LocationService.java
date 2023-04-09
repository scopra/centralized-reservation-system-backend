package com.ontime.crrs.persistence.location.service;

import com.ontime.crrs.persistence.location.entity.LocationEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface LocationService {

    Optional<LocationEntity> findLocationById(UUID id);

    LocationEntity updateLocation(LocationEntity location);

    boolean checkIfLocationExistsById(UUID id);

    void deleteLocationById(UUID id);

    void deleteAllLocations();
}
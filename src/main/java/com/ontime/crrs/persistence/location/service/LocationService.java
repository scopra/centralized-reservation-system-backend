package com.ontime.crrs.persistence.location.service;

import com.ontime.crrs.persistence.location.entity.LocationEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public interface LocationService {


    LocationEntity findLocationById(UUID id) throws NoSuchElementException;

    LocationEntity updateLocation(LocationEntity location);

    boolean checkIfLocationExistsById(UUID id);

    void deleteLocationById(UUID id);

    void deleteAllLocations();

}
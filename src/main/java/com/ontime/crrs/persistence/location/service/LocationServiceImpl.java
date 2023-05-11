package com.ontime.crrs.persistence.location.service;

import com.ontime.crrs.persistence.location.entity.LocationEntity;
import com.ontime.crrs.persistence.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository repository;


    public LocationEntity findLocationById(UUID id) throws NoSuchElementException {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Location with ID: " + id + " not found."));
    }

    public LocationEntity updateLocation(LocationEntity location) {
        return repository.save(location);
    }

    public boolean checkIfLocationExistsById(UUID id) {
        return repository.existsById(id);
    }

    public void deleteLocationById(UUID id) {
        repository.deleteById(id);
    }

    public void deleteAllLocations() {
        repository.deleteAll();
    }

}
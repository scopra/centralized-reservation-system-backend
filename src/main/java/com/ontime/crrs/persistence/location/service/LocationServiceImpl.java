package com.ontime.crrs.persistence.location.service;

import com.ontime.crrs.persistence.location.entity.LocationEntity;
import com.ontime.crrs.persistence.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository repository;

    public Optional<LocationEntity> findLocationById(UUID id) {
        return repository.findById(id);
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
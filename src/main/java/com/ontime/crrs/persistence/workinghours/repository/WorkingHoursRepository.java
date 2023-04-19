package com.ontime.crrs.persistence.workinghours.repository;

import com.ontime.crrs.persistence.workinghours.entity.WorkingHoursEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WorkingHoursRepository extends JpaRepository<WorkingHoursEntity, UUID> {

    Optional<WorkingHoursEntity> findWorkingHoursByRestaurant_Name(String name);

    Optional<WorkingHoursEntity> findWorkingHoursByRestaurant_Id(UUID id);
}
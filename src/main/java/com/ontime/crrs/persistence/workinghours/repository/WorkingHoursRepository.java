package com.ontime.crrs.persistence.workinghours.repository;

import com.ontime.crrs.persistence.workinghours.entity.WorkingHoursEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkingHoursRepository extends JpaRepository<WorkingHoursEntity, UUID> {
}
package com.ontime.crrs.persistence.tableoccupancy.repository;

import com.ontime.crrs.persistence.tableoccupancy.entity.TableOccupancyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TableOccupancyRepository extends JpaRepository<TableOccupancyEntity, UUID> {
}
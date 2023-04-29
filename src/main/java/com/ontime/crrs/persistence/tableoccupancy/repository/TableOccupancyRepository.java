package com.ontime.crrs.persistence.tableoccupancy.repository;

import com.ontime.crrs.persistence.tableoccupancy.entity.TableOccupancyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TableOccupancyRepository extends JpaRepository<TableOccupancyEntity, UUID> {

    List<TableOccupancyEntity> getTableOccupanciesByTable_Id(UUID id);

}
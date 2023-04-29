package com.ontime.crrs.persistence.tableoccupancy.service;

import com.ontime.crrs.persistence.tableoccupancy.entity.TableOccupancyEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface TableOccupancyService {

    TableOccupancyEntity getTableOccupancyById(UUID id);

    List<TableOccupancyEntity> getOccupanciesForTable(UUID id);

}
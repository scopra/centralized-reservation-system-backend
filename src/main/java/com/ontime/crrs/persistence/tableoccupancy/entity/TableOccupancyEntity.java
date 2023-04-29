package com.ontime.crrs.persistence.tableoccupancy.entity;

import com.ontime.crrs.persistence.table.entity.TableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.UUID;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "table_occupancy")
public class TableOccupancyEntity {

    @Id
    @GeneratedValue
    @Column(
            name = "table_occupancy_id",
            updatable = false,
            columnDefinition = "uuid"
    )
    private UUID id;

    @Column(
            name = "from",
            nullable = false
    )
    private LocalTime from;

    @Column(
            name = "to",
            nullable = false
    )
    private LocalTime to;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableEntity table;

}
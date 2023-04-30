package com.ontime.crrs.persistence.tableoccupancy.entity;

import com.ontime.crrs.persistence.table.entity.TableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
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
            name = "date",
            nullable = false
    )
    private LocalDate date;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableOccupancyEntity that = (TableOccupancyEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(from, that.from) && Objects.equals(to, that.to) && Objects.equals(table, that.table);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
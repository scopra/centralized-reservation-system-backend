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
@ToString(exclude = "table")
@Table(name = "table_occupancy")
public class TableOccupancyEntity {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Column(
            name = "table_occupancy_id",
            updatable = false,
            columnDefinition = "uuid"
    )
    private UUID id;

    @Column(
            name = "reservation_date",
            nullable = false
    )
    private LocalDate reservationDate;

    @Column(
            name = "reserved_from",
            nullable = false
    )
    private LocalTime reservedFrom;

    @Column(
            name = "reserved_to",
            nullable = false
    )
    private LocalTime reservedTo;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableEntity table;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableOccupancyEntity that = (TableOccupancyEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(reservationDate, that.reservationDate) &&
                Objects.equals(reservedFrom, that.reservedFrom) &&
                Objects.equals(reservedTo, that.reservedTo) &&
                Objects.equals(table, that.table);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
package com.ontime.crrs.helper;


import com.ontime.crrs.persistence.table.entity.TableEntity;
import org.springframework.stereotype.Component;

@Component
public class TableTestHelper {

    public static final int TABLE_CAPACITY = 10;
    public static final boolean TABLE_OCCUPANCY_STATUS = false;

    public static TableEntity buildDefaultEntityWithCapacity(int capacity) {
        return TableEntity.builder()
                .occupancyStatus(false)
                .capacity(capacity)
                .build();
    }

    public static TableEntity buildDefaultEntityWithStatus(boolean status) {
        return TableEntity.builder()
                .occupancyStatus(status)
                .capacity(10)
                .build();
    }

    public static TableEntity buildCustomTableEntity(boolean status, int capacity) {
        return TableEntity.builder()
                .occupancyStatus(status)
                .capacity(capacity)
                .build();
    }


}

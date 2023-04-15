package com.ontime.crrs.business.table.exception;

import java.util.UUID;

public class TableNotFoundException  extends RuntimeException{

    public TableNotFoundException(String message) {
        super(message);
    }

    public TableNotFoundException(UUID id) {
        super("Could not find table with ID: " + id);
    }

}
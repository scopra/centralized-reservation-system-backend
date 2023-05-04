package com.ontime.crrs.business.table.exception;

public class TableUnavailableException extends RuntimeException {

    public TableUnavailableException(String restaurant, int capacity) {
        super("Could not find available table for restaurant " + restaurant + " with capacity " + capacity + ".");
    }

}
package com.ontime.crrs.test_classes;

public enum CustomerType {

    LOYAL, NEW, DISSATISFIED;

    public String getValue() {
        return this.toString();
    }
}
package com.ontime.crrs.rules;

public enum CustomerType {

    LOYAL, NEW, DISSATISFIED;

    public String getValue() {
        return this.toString();
    }

}
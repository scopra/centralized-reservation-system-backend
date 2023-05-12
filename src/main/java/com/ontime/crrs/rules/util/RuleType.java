package com.ontime.crrs.rules.util;

public enum RuleType {

    QUIET_TIMES, HAPPY_HOURS, DAY_OF_WEEK, GROUP_SPECIALS;

    public String getValue() {
        return this.toString();
    }

}
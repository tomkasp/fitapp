package com.tomkasp.fitapp.activitytrackers.domain;

/**
 * @author Tomasz Kasprzycki
 */
public enum ActivityType {

    BIKING("BIKING"),
    RUNNING("RUNNING"),
    SWIMMING("SWIMMING"),
    FOOTBALL("FOOTBALL"),
    BASKETBALL("BASKETBALL");

    private final String activityType;

    ActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityType() {
        return activityType;
    }
}

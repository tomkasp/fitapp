package com.tomkasp.fitapp.activitytrackers.domain;

/**
 * @author Tomasz Kasprzycki
 */
public enum ActivitySource {

    STRAVA("STRAVA"),
    GARMIN("GARMIN"),
    BASE("BASE");

    private String source;
    ActivitySource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }
}

package com.tomkasp.fitapp.activitytrackers.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.io.Serializable;

/**
 * @author Tomasz Kasprzycki
 */

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class StravaActivitiesDto implements Serializable {

    String type;
    String name;
    Float maxSpeed;
    Float distance;

    public StravaActivitiesDto type(final String type) {
        this.type = type;
        return this;
    }

    public StravaActivitiesDto name(final String name) {
        this.name = name;
        return this;
    }

    public StravaActivitiesDto maxSpeed(final Float maxSpeed) {
        this.maxSpeed = maxSpeed;
        return this;
    }

    public StravaActivitiesDto distance(final Float distance) {
        this.distance = distance;
        return this;
    }


}
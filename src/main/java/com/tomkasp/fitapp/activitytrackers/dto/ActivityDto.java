package com.tomkasp.fitapp.activitytrackers.dto;

import com.tomkasp.fitapp.activitytrackers.domain.ActivitySource;
import com.tomkasp.fitapp.activitytrackers.domain.ActivityType;
import org.joda.time.DateTime;

/**
 * @author Tomasz Kasprzycki
 */
public class ActivityDto {

    private Long id;
    private ActivitySource activitySource;
    private String integrationId;
    private ActivityType activityType;
    private String activityAverageSpeed;
    private String activityDuration;
    private DateTime dateTime;

    public ActivityDto id(final Long id) {
        this.id = id;
        return this;
    }

    public ActivityDto activitySource(final ActivitySource activitySource) {
        this.activitySource = activitySource;
        return this;
    }

    public ActivityDto integrationId(final String integrationId) {
        this.integrationId = integrationId;
        return this;
    }

    public ActivityDto activityType(final ActivityType activityType) {
        this.activityType = activityType;
        return this;
    }

    public ActivityDto activityAverageSpeed(final String activityAverageSpeed) {
        this.activityAverageSpeed = activityAverageSpeed;
        return this;
    }

    public ActivityDto activityDuration(final String activityDuration) {
        this.activityDuration = activityDuration;
        return this;
    }

    public ActivityDto dateTime(final DateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }


}

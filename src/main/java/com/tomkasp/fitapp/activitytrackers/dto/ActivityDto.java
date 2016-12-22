package com.tomkasp.fitapp.activitytrackers.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.tomkasp.fitapp.activitytrackers.domain.ActivitySource;
import com.tomkasp.fitapp.activitytrackers.domain.ActivityType;
import org.joda.time.LocalDateTime;

import java.io.Serializable;

/**
 * @author Tomasz Kasprzycki
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class ActivityDto implements Serializable {

    private Long id;
    private ActivitySource activitySource;
    private String integrationId;
    private ActivityType activityType;
    private String activityAverageSpeed;
    private String activityDuration;
    private String dateTime;

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

    public ActivityDto dateTime(final LocalDateTime dateTime) {
        this.dateTime = dateTime.toString("dd/MM/yyyy HH:mm:ss");
        return this;
    }


}

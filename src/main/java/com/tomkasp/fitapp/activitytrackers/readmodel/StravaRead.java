package com.tomkasp.fitapp.activitytrackers.readmodel;

import com.tomkasp.fitapp.activitytrackers.infrastructure.dto.ActivityDto;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
public interface StravaRead {

    StravaLinkWrapper stravaUrlGenerator();

    List<ActivityDto> getActivities();
}

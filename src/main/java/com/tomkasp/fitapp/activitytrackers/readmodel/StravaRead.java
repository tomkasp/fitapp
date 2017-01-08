package com.tomkasp.fitapp.activitytrackers.readmodel;

import com.tomkasp.fitapp.activitytrackers.dto.StravaActivitiesDto;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
public interface StravaRead {

    StravaLinkWrapper stravaUrlGenerator();

    List<StravaActivitiesDto> getActivities();
}

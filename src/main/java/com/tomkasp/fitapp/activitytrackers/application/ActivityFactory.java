package com.tomkasp.fitapp.activitytrackers.application;

import com.tomkasp.fitapp.activitytrackers.domain.Activity;
import com.tomkasp.fitapp.activitytrackers.dto.ActivityDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
@Component
class ActivityFactory {

    public ActivityDto buidDto(Activity activity) {
        ActivityDto activityDto = new ActivityDto()
            .activityAverageSpeed(activity.getActivityAverageSpeed())
            .activityDuration(activity.getActivityDuration())
            .activitySource(activity.getActivitySource())
            .activityType(activity.getActivityType())
            .dateTime(activity.getDateTime())
            .id(activity.getId())
            .integrationId(activity.getIntegrationId());
        return activityDto;
    }

    List<ActivityDto> buildDtos(List<Activity> activities) {
        List<ActivityDto> activityDtos = new ArrayList<>();
        for (Activity activity : activities) {
            activityDtos.add(buidDto(activity));
        }
        return activityDtos;
    }

    List<ActivityDto> buildEmptyDtoList(){
        return Collections.emptyList();
    }
}

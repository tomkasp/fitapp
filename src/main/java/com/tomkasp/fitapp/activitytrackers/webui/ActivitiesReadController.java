package com.tomkasp.fitapp.activitytrackers.webui;

import com.tomkasp.fitapp.activitytrackers.application.ActivitiesReadService;
import com.tomkasp.fitapp.activitytrackers.domain.TrackersMetadata;
import com.tomkasp.fitapp.activitytrackers.dto.ActivityDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping("/api/activities")
public class ActivitiesReadController {

    private final ActivitiesReadService activitiesReadService;

    public ActivitiesReadController(ActivitiesReadService activitiesReadService) {
        this.activitiesReadService = activitiesReadService;
    }

    @GetMapping
    public List<ActivityDto> getAll() {
        return activitiesReadService.readAllUserActivities();
    }

}

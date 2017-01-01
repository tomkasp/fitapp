package com.tomkasp.fitapp.activitytrackers.webui;

import com.tomkasp.fitapp.activitytrackers.readmodel.impl.JpaActivitiesReadService;
import com.tomkasp.fitapp.activitytrackers.dto.ActivityDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger log = LoggerFactory.getLogger(ActivitiesReadController.class);
    private final JpaActivitiesReadService jpaActivitiesReadService;

    public ActivitiesReadController(JpaActivitiesReadService jpaActivitiesReadService) {
        this.jpaActivitiesReadService = jpaActivitiesReadService;
    }

    @GetMapping
    public List<ActivityDto> getAll() {
        return jpaActivitiesReadService.readAllUserActivities();
    }

}

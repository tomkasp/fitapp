package com.tomkasp.fitapp.activitytrackers.application;

import com.tomkasp.fitapp.activitytrackers.dto.ActivityDto;
import com.tomkasp.fitapp.activitytrackers.infrastructure.ActivityRepository;
import com.tomkasp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class ActivitiesReadService {

    private final ActivityRepository activityRepository;
    private final UserService userService;
    private final ActivityFactory activityFactory;

    @Autowired
    public ActivitiesReadService(ActivityRepository activityRepository, UserService userService, ActivityFactory activityFactory) {
        this.activityRepository = activityRepository;
        this.userService = userService;
        this.activityFactory = activityFactory;
    }

    public List<ActivityDto> readAllUserActivities() {
        final Long id = userService.getUserWithAuthorities().getId();
        return activityRepository.findByUserId(id)
            .map(result ->
                activityFactory.buildDtos(result)
            ).orElse(activityFactory.buildEmptyDtoList());
    }

}

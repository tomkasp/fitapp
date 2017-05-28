package com.tomkasp.fitapp.activitytrackers.readmodel.impl;

import com.tomkasp.fitapp.activitytrackers.infrastructure.ActivityRepository;
import com.tomkasp.fitapp.activitytrackers.infrastructure.dto.ActivityDto;
import com.tomkasp.fitapp.activitytrackers.readmodel.ActivitiesReadService;
import com.tomkasp.fitapp_common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class JpaActivitiesReadService implements ActivitiesReadService {

    private final ActivityRepository activityRepository;
    private final UserService userService;
    private final ActivityFactory activityFactory;

    @Autowired
    public JpaActivitiesReadService(ActivityRepository activityRepository, UserService userService, ActivityFactory activityFactory) {
        this.activityRepository = activityRepository;
        this.userService = userService;
        this.activityFactory = activityFactory;
    }

    @Override
    public List<ActivityDto> readAllUserActivities() {
        final Long id = userService.getUserWithAuthorities().getId();
        return activityRepository.findByUserIdOrderByDateTimeAsc(id)
            .map(result ->
                activityFactory.buildDtos(result)
            ).orElse(activityFactory.buildEmptyDtoList());
    }

}

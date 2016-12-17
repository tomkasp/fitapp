package com.tomkasp.fitapp.activitytrackers.webui;

import com.tomkasp.fitapp.activitytrackers.domain.TrackersMetadata;
import com.tomkasp.fitapp.activitytrackers.infrastructure.TrackersMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping("/api/activities")
public class ActivitiesController {

    private final TrackersMetadataRepository activityRepository;

    @Autowired
    public ActivitiesController(TrackersMetadataRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @GetMapping
    public List<TrackersMetadata> getAll(){
        final List<TrackersMetadata> all = activityRepository.findAll();
        return all;
    }

}

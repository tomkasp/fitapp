package com.tomkasp.fitapp.activitytrackers.webui;

import com.tomkasp.common.service.UserService;
import com.tomkasp.fitapp.activitytrackers.domain.TrackersData;
import com.tomkasp.fitapp.activitytrackers.domain.TrackersMetadata;
import com.tomkasp.fitapp.activitytrackers.infrastructure.TrackersDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping("/api/trackers")
public class TrackersDataController {

    @Autowired
    TrackersDataRepository trackersDataRepository;

    @Autowired
    UserService userService;

    private final Logger log = LoggerFactory.getLogger(TrackersDataController.class);

    @GetMapping(value = "/test")
    @Transactional
    public void test() {
        TrackersMetadata trackersMetadata = new TrackersMetadata();
        trackersMetadata.email("tomkasp@gmail.com");
        TrackersData trackersData = new TrackersData();
        trackersData.user(userService.getUserWithAuthorities());
//        trackersData.trackerMetadata(trackersMetadata);

        trackersDataRepository.save(trackersData);
    }

    @GetMapping(value = "/getAll")
    public void readAll(){
        final List<TrackersData> all = trackersDataRepository.findAll();
        log.debug(String.valueOf(all));
    }

}

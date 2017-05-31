package com.tomkasp.fitapp.activitytrackers.application.commands.handlers;

import com.tomkasp.common.service.UserService;
import com.tomkasp.fitapp.activitytrackers.application.commands.ExchangeStravaTokenCommand;
import com.tomkasp.fitapp.activitytrackers.domain.ActivitySource;
import com.tomkasp.fitapp.activitytrackers.domain.TrackersData;
import com.tomkasp.fitapp.activitytrackers.infrastructure.TrackersDataRepository;
import com.tomkasp.fitapp.cqrs.command.handler.CommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class UpdateStravaMetadata implements CommandHandler<ExchangeStravaTokenCommand, Void> {

    private final Logger log = LoggerFactory.getLogger(UpdateStravaMetadata.class);

    private final UserService userService;
    private final TrackersDataRepository trackersDataRepository;

    @Autowired
    public UpdateStravaMetadata(UserService userService, TrackersDataRepository trackersDataRepository) {
        this.userService = userService;
        this.trackersDataRepository = trackersDataRepository;
    }

    @Override
    public Void handle(ExchangeStravaTokenCommand command) {
        log.info("updating strava metadata");
        final Optional<TrackersData> trackersData = trackersDataRepository.findByUserId(userService.getUserWithAuthorities().getId());
        trackersData.map(result -> {
            log.info("updating trackers data entry with code: {}", command.getCode());
//                result.getTrackerMetadata().setStravaCode(command.getCode());
                return trackersDataRepository.save(result);
            }
        ).orElseGet(() -> {
            log.info("creating new trackersdata entry");
            TrackersData freshTrackersData = new TrackersData();
            freshTrackersData.user(userService.getUserWithAuthorities())
                .activitySource(ActivitySource.STRAVA);
//                .trackerMetadata(new TrackersMetadata().setStravaCode(command.getCode()));
            return trackersDataRepository.save(freshTrackersData);
        });
        return null;
    }
}

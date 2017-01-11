package com.tomkasp.fitapp.activitytrackers.readmodel.impl;

import com.tomkasp.fitapp.activitytrackers.domain.ActivitySource;
import com.tomkasp.fitapp.activitytrackers.domain.ActivityType;
import com.tomkasp.fitapp.activitytrackers.domain.TrackersData;
import com.tomkasp.fitapp.activitytrackers.dto.ActivityDto;
import com.tomkasp.fitapp.activitytrackers.infrastructure.TrackersDataRepository;
import com.tomkasp.fitapp.activitytrackers.readmodel.StravaLinkWrapper;
import com.tomkasp.fitapp.activitytrackers.readmodel.StravaRead;
import com.tomkasp.service.UserService;
import javastrava.api.v3.auth.TokenManager;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.auth.model.TokenResponse;
import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.rest.API;
import javastrava.api.v3.rest.AuthorisationAPI;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class StravaReadImpl implements StravaRead {

    private final TrackersDataRepository trackersDataRepository;
    private final UserService userService;

    @Autowired
    public StravaReadImpl(TrackersDataRepository trackersDataRepository, UserService userService) {
        this.trackersDataRepository = trackersDataRepository;
        this.userService = userService;
    }

    @Override
    public StravaLinkWrapper stravaUrlGenerator() {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://www.strava.com/oauth/authorize")
            .queryParam("client_id", "14842")
            .queryParam("response_type", "code")
            .queryParam("redirect_uri", "https://athleticspot.com/api/strava/exchange")
            .queryParam("scope", "write");

        return new StravaLinkWrapper().activationLink(builder.build().encode().toUriString());
    }


    @Override
    public List<ActivityDto> getActivities() {

        Function<StravaActivity, ActivityDto> createActivitiesDtoFunction = stravaActivity ->
            new ActivityDto()
                .activityAverageSpeed(stravaActivity.getAverageSpeed().toString())
                .activityDuration(stravaActivity.getElapsedTime().toString())
                .activityType(ActivityType.valueOf(stravaActivity.getType().toString()))
                .integrationId(stravaActivity.getExternalId())
                .dateTime(new LocalDateTime(stravaActivity.getStartDateLocal()))
                .activitySource(ActivitySource.STRAVA);

        Optional<Token> optionalAPI = Optional.ofNullable(TokenManager.instance().retrieveTokenWithExactScope("tomkasp@gmail.com"));

        return optionalAPI.map(token -> {
            API api = new API(token);
            StravaActivity[] stravaActivities = api.listAuthenticatedAthleteActivities(null, null, 1, 10);
            return Arrays.stream(stravaActivities).map(createActivitiesDtoFunction).collect(Collectors.toList());
        }).orElseGet(() -> {
                final Optional<TrackersData> trackersDataOptional = trackersDataRepository.findByUserId(userService.getUserWithAuthorities().getId());
                return trackersDataOptional.map(trackersData -> {
                    AuthorisationAPI auth = API.authorisationInstance();
                    TokenResponse response = auth.tokenExchange(14842, "91ad80ea231505275883acc75d7c088c1cf07773", trackersData.getTrackerMetadata().getStravaCode());
                    Token token = new Token(response);
                    TokenManager.instance().storeToken(token);
                    API api = new API(token);
                    StravaActivity[] stravaActivities = api.listAuthenticatedAthleteActivities(null, null, 1, 10);
                    return Arrays.stream(stravaActivities).map(createActivitiesDtoFunction).collect(Collectors.toList());
                }).get();

            }
        );
    }
}

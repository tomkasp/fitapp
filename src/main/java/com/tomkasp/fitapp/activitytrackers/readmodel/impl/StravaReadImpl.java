package com.tomkasp.fitapp.activitytrackers.readmodel.impl;

import com.tomkasp.fitapp.activitytrackers.domain.TrackersData;
import com.tomkasp.fitapp.activitytrackers.dto.StravaActivitiesDto;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
    public List<StravaActivitiesDto> getActivities() {

        Optional<Token> optionalAPI = Optional.ofNullable(TokenManager.instance().retrieveTokenWithExactScope("tomkasp@gmail.com"));

        return optionalAPI.map(token -> {
            API api = new API(token);
            StravaActivity[] stravaActivities = api.listAuthenticatedAthleteActivities(null, null, 1, 10);
            return Arrays.stream(stravaActivities).map(stravaActivity -> new StravaActivitiesDto()
                .distance(stravaActivity.getDistance())
                .maxSpeed(stravaActivity.getMaxSpeed())
                .name(stravaActivity.getName())
                .type(stravaActivity.getType().toString())).collect(Collectors.toList());
        }).orElseGet(() -> {
                final Optional<TrackersData> trackersDataOptional = trackersDataRepository.findByUserId(userService.getUserWithAuthorities().getId());
                return trackersDataOptional.map(trackersData -> {
                    AuthorisationAPI auth = API.authorisationInstance();
                    TokenResponse response = auth.tokenExchange(14842, "91ad80ea231505275883acc75d7c088c1cf07773", trackersData.getTrackerMetadata().getStravaCode());
                    Token token = new Token(response);
                    TokenManager.instance().storeToken(token);
                    API api = new API(token);
                    StravaActivity[] stravaActivities = api.listAuthenticatedAthleteActivities(null, null, 1, 10);
                    return Arrays.stream(stravaActivities).map(stravaActivity -> new StravaActivitiesDto()
                        .distance(stravaActivity.getDistance())
                        .maxSpeed(stravaActivity.getMaxSpeed())
                        .name(stravaActivity.getName())
                        .type(stravaActivity.getType().toString())).collect(Collectors.toList());
                }).get();

            }
        );
    }
}

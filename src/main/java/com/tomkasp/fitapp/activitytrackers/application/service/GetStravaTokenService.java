package com.tomkasp.fitapp.activitytrackers.application.service;

import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.auth.model.TokenResponse;
import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.rest.API;
import javastrava.api.v3.rest.AuthorisationAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class GetStravaTokenService {

    private final Logger log = LoggerFactory.getLogger(GetStravaTokenService.class);
    private final RestTemplate restTemplate = new RestTemplate();

    private API api;


    public String requestAccess() {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://www.strava.com/oauth/authorize")
            .queryParam("client_id", "14842")
            .queryParam("response_type", "code")
            .queryParam("redirect_uri", "https://fitnowcloud.com/api/strava/exchange")
            .queryParam("scope", "write")
            .queryParam("approval_prompt", "force");

        return builder.build().encode().toUriString();
    }

    public StravaActivity[] exchangeToken(String code) {
        AuthorisationAPI auth = API.authorisationInstance();
        TokenResponse response = auth.tokenExchange(14842, "91ad80ea231505275883acc75d7c088c1cf07773", code);

        Token token = new Token(response);
        api = new API(token);
        final StravaActivity[] stravaActivities = api.listAuthenticatedAthleteActivities(null, null, 1, 10);

        return stravaActivities;
    }

    public StravaActivity[] getActivities() {
        final StravaActivity[] stravaActivities = api.listAuthenticatedAthleteActivities(null, null, 1, 10);

        return stravaActivities;
    }
}

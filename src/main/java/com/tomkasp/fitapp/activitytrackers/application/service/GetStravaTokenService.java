package com.tomkasp.fitapp.activitytrackers.application.service;

import com.tomkasp.fitapp.activitytrackers.dto.StravaActivitiesDto;
import javastrava.api.v3.auth.TokenManager;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.auth.model.TokenResponse;
import javastrava.api.v3.auth.ref.AuthorisationScope;
import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.rest.API;
import javastrava.api.v3.rest.AuthorisationAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class GetStravaTokenService {

    private final Logger log = LoggerFactory.getLogger(GetStravaTokenService.class);
    private final RestTemplate restTemplate = new RestTemplate();

    private String code = ""; //Store in a database


    public String requestAccess() {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://www.strava.com/oauth/authorize")
            .queryParam("client_id", "14842")
            .queryParam("response_type", "code")
            .queryParam("redirect_uri", "https://fitnowcloud.com/api/strava/exchange")
            .queryParam("scope", "write");

        return builder.build().encode().toUriString();
    }


    public List<StravaActivitiesDto> exchangeToken(String code) {
        log.debug("strava code value: {}", code);
        this.code = code;
        AuthorisationAPI auth = API.authorisationInstance();
        TokenResponse response = auth.tokenExchange(14842, "91ad80ea231505275883acc75d7c088c1cf07773", code);

        Token token = new Token(response);
        TokenManager.instance().storeToken(token);
        log.debug("strava token value: {}", token);

        return null;
    }

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
            //TODO handle situation where there is no token
        }).orElse(exchangeToken(this.code));

    }

    public void activate() {
        final Object forObject = restTemplate.getForObject(requestAccess(), Object.class);
        log.debug("get response: {}", forObject);
    }
}

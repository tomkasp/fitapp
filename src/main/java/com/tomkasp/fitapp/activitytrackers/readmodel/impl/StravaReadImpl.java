package com.tomkasp.fitapp.activitytrackers.readmodel.impl;

import com.tomkasp.fitapp.activitytrackers.readmodel.StravaLinkWrapper;
import com.tomkasp.fitapp.activitytrackers.readmodel.StravaRead;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class StravaReadImpl implements StravaRead {

    @Override
    public StravaLinkWrapper stravaUrlGenerator() {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://www.strava.com/oauth/authorize")
            .queryParam("client_id", "14842")
            .queryParam("response_type", "code")
            .queryParam("redirect_uri", "https://fitnowcloud.com/api/strava/exchange")
            .queryParam("scope", "write");

        return new StravaLinkWrapper().activationLink(builder.build().encode().toUriString());
    }
}

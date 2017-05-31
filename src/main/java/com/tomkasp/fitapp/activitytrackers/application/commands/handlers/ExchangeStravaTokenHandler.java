package com.tomkasp.fitapp.activitytrackers.application.commands.handlers;

import com.tomkasp.common.service.UserService;
import com.tomkasp.fitapp.activitytrackers.application.commands.ExchangeStravaTokenCommand;
import com.tomkasp.fitapp.activitytrackers.infrastructure.TrackersDataRepository;
import com.tomkasp.fitapp.cqrs.annotations.CommandHandlerAnnotation;
import com.tomkasp.fitapp.cqrs.command.handler.CommandHandler;
import javastrava.api.v3.auth.TokenManager;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.auth.model.TokenResponse;
import javastrava.api.v3.rest.API;
import javastrava.api.v3.rest.AuthorisationAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Tomasz Kasprzycki
 */
@CommandHandlerAnnotation
public class ExchangeStravaTokenHandler implements CommandHandler<ExchangeStravaTokenCommand, Void> {

    private final Logger log = LoggerFactory.getLogger(ExchangeStravaTokenHandler.class);

    @Autowired
    public ExchangeStravaTokenHandler(TrackersDataRepository trackersDataRepository, UserService userService) {
    }

    @Override
    public Void handle(ExchangeStravaTokenCommand command) {
        log.info("exchanging strava token");
        log.info("strava code value: {}", command.getCode());
        AuthorisationAPI auth = API.authorisationInstance();

        TokenResponse response = auth.tokenExchange(14842, "91ad80ea231505275883acc75d7c088c1cf07773", command.getCode());
        Token token = new Token(response);
        TokenManager.instance().storeToken(token);
        return null;
    }
}

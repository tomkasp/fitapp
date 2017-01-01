package com.tomkasp.fitapp.activitytrackers.application.commands.handlers;

import com.tomkasp.fitapp.activitytrackers.application.GetStravaTokenService;
import com.tomkasp.fitapp.activitytrackers.application.commands.ExchangeStravaTokenCommand;
import com.tomkasp.fitapp.cqrs.annotations.CommandHandlerAnnotation;
import com.tomkasp.fitapp.cqrs.command.handler.CommandHandler;
import javastrava.api.v3.auth.TokenManager;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.auth.model.TokenResponse;
import javastrava.api.v3.rest.API;
import javastrava.api.v3.rest.AuthorisationAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Tomasz Kasprzycki
 */
@CommandHandlerAnnotation
public class ExchangeStravaTokenHandler implements CommandHandler<ExchangeStravaTokenCommand, Void> {

    private final Logger log = LoggerFactory.getLogger(GetStravaTokenService.class);

    @Override
    public Void handle(ExchangeStravaTokenCommand command) {
        log.debug("strava code value: {}", command.getCode());
        AuthorisationAPI auth = API.authorisationInstance();
        TokenResponse response = auth.tokenExchange(14842, "91ad80ea231505275883acc75d7c088c1cf07773", command.getCode());

        Token token = new Token(response);
        TokenManager.instance().storeToken(token);
        log.debug("strava token value: {}", token);
        return null;
    }
}

package com.tomkasp.fitapp.activitytrackers.webui;

import com.tomkasp.fitapp.activitytrackers.application.service.GetStravaTokenService;
import javastrava.api.v3.model.StravaActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping("/api/strava")
public class StravaController {

    private final GetStravaTokenService getStravaTokenService;

    @Autowired
    public StravaController(GetStravaTokenService getStravaTokenService) {
        this.getStravaTokenService = getStravaTokenService;
    }

    @GetMapping
    public ResponseWrapper getAuthorizationLink() {
        return new ResponseWrapper(getStravaTokenService.requestAccess());
    }

    @GetMapping("/exchange")
    public StravaActivity[] tokenExchange(@RequestParam String code, @RequestParam String state) {
        return getStravaTokenService.exchangeToken(code);
    }

    @GetMapping("/activities")
    public StravaActivity[] getActivities(){
        return getStravaTokenService.getActivities();
    }

    private static final class ResponseWrapper {
        private String activationLink;


        private ResponseWrapper(String activationLink) {
            this.activationLink = activationLink;
        }

        public String getActivationLink() {
            return activationLink;
        }

        public ResponseWrapper setActivationLink(String activationLink) {
            this.activationLink = activationLink;
            return this;
        }
    }


}

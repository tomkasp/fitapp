package com.tomkasp.fitapp.activitytrackers.webui;

import com.tomkasp.fitapp.activitytrackers.application.GetStravaTokenService;
import com.tomkasp.fitapp.activitytrackers.dto.StravaActivitiesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
    public void tokenExchange(@RequestParam String code, @RequestParam String state, HttpServletResponse response) throws IOException {
        getStravaTokenService.exchangeToken(code);
        response.sendRedirect("/#/trackers");
    }

    @GetMapping("/activities")
    public List<StravaActivitiesDto> getActivities(){
        return getStravaTokenService.getActivities();
    }

    @GetMapping("/activate")
    public void activities(){
         getStravaTokenService.activate();
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

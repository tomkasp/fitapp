package com.tomkasp.fitapp.activitytrackers.webui;

import com.tomkasp.fitapp.activitytrackers.readmodel.StravaLinkWrapper;
import com.tomkasp.fitapp.activitytrackers.readmodel.StravaRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping("/api/strava")
public class StravaReadController {

    private final StravaRead stravaRead;

    @Autowired
    public StravaReadController(StravaRead stravaRead) {
        this.stravaRead = stravaRead;
    }

    @GetMapping
    public StravaLinkWrapper getAuthorizationLink() {
        return stravaRead.stravaUrlGenerator();
    }


}

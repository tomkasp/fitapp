package com.tomkasp.fitapp.activitytrackers.webui;

import com.tomkasp.fitapp.activitytrackers.dto.ActivityDto;
import com.tomkasp.fitapp.activitytrackers.readmodel.StravaLinkWrapper;
import com.tomkasp.fitapp.activitytrackers.readmodel.StravaRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping(value = "/activationlink")
    public StravaLinkWrapper getAuthorizationLink() {
        return stravaRead.stravaUrlGenerator();
    }

    @GetMapping("/activities")
    public List<ActivityDto> getActivities() {
        return stravaRead.getActivities();
    }


}

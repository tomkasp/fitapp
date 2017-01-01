package com.tomkasp.fitapp.activitytrackers.webui;

import com.tomkasp.fitapp.activitytrackers.application.GetStravaTokenService;
import com.tomkasp.fitapp.activitytrackers.application.commands.ExchangeStravaTokenCommand;
import com.tomkasp.fitapp.cqrs.command.Gate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping("/api/strava")
public class StravaWriteController {

    private final GetStravaTokenService getStravaTokenService;
    private final Gate gate;

    @Autowired
    public StravaWriteController(GetStravaTokenService getStravaTokenService, Gate gate) {
        this.getStravaTokenService = getStravaTokenService;
        this.gate = gate;
    }

    @GetMapping("/exchange")
    public void tokenExchange(@RequestParam String code, @RequestParam String state, HttpServletResponse response) throws IOException {
        gate.dispatch(new ExchangeStravaTokenCommand(code));
        response.sendRedirect("/#/trackers");
    }
}

package com.tomkasp.training.application.command;

import com.tomkasp.common.common.command.ResponseAwareDomainCommand;
import com.tomkasp.training.domain.RaceResult;
import org.springframework.data.geo.Distance;

/**
 * @author Tomasz Kasprzycki
 */
public class CalculateAthleteTrainingCommand extends ResponseAwareDomainCommand<Long> {

    private final Distance trainingDistance;
    private final RaceResult lastRaceResult;

    public CalculateAthleteTrainingCommand(Distance trainingDistance, RaceResult lastRaceResult) {
        this.trainingDistance = trainingDistance;
        this.lastRaceResult = lastRaceResult;
    }

    public Distance getTrainingDistance() {
        return trainingDistance;
    }

    public RaceResult getLastRaceResult() {
        return lastRaceResult;
    }
}

package com.tomkasp.training.application.command;

import com.tomkasp.common.common.command.ResponseAwareDomainCommand;
import com.tomkasp.training.domain.RaceResult;
import com.tomkasp.training.domain.RunCategory;

/**
 * @author Tomasz Kasprzycki
 */
public class CalculateAthleteTrainingCommand extends ResponseAwareDomainCommand<Long> {

    private final RunCategory trainingRunCategory;
    private final RaceResult lastRaceResult;

    public CalculateAthleteTrainingCommand(RunCategory trainingRunCategory, RaceResult lastRaceResult) {
        this.trainingRunCategory = trainingRunCategory;
        this.lastRaceResult = lastRaceResult;
    }

    public RunCategory getTrainingRunCategory() {
        return trainingRunCategory;
    }

    public RaceResult getLastRaceResult() {
        return lastRaceResult;
    }
}

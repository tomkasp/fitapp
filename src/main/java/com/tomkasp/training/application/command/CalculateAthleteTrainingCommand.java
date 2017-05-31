package com.tomkasp.training.application.command;

import org.springframework.data.geo.Distance;

/**
 * @author Tomasz Kasprzycki
 */
public class CalculateAthleteTrainingCommand {

    private final Distance distanceToTrain;


    public CalculateAthleteTrainingCommand(Distance distance) {
        this.distanceToTrain = distance;
    }

    public Distance getDistanceToTrain() {
        return distanceToTrain;
    }
}

package com.tomkasp.training.domain;

/**
 * @author Tomasz Kasprzycki
 */
public class TrainingPlan {

    private final Long fiveKDistanceInMeters = 5000L;

    public double calculateRacePredictor(RaceResult raceResult) {
        final double t2 = raceResult.getTimeInSeconds() *
            Math.pow(((float) fiveKDistanceInMeters / raceResult.getDistanceInMeters()), 1.06d);
        return t2;
    }

}

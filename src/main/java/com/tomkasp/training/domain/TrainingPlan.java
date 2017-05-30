package com.tomkasp.training.domain;

/**
 * @author Tomasz Kasprzycki
 */
public class TrainingPlan {

    private final Long fiveKDistanceInMeters = 5000L;

    public double calculateRiegelRacePredictor(RaceResult raceResult) {
        final double t2 = raceResult.getTimeInSeconds() *
            Math.pow(((float) fiveKDistanceInMeters / raceResult.getDistanceInMeters()), 1.06d);
        return t2;
    }

    public void calculateTempo() {

    }

    public double calculateEasyTempo(double riegelPredictorTimeInSeconds) {
        return (riegelPredictorTimeInSeconds / TempoParams.EASY_TEMPO) / 60 + 5;
    }

    public double calculateMaximumOxygenSpeedFormTempo(double riegelPredictorTimeInSeconds) {
        return (riegelPredictorTimeInSeconds / TempoParams.MAXIMUM_OXYGEN) / 60 + 3;
    }

    public double calculateLonRunMaxTempo(double riegelPredictorTimeInSeconds) {
        return (riegelPredictorTimeInSeconds / TempoParams.LONG_RUN_MAX) / 60 + 2;
    }

    public double calculateLongRunMinTempo() {
        return 0;
    }

    public double calculateYasso800Tempo() {
        return 0;
    }

}

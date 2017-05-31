package com.tomkasp.training.domain;

import com.tomkasp.common.domain.ValueObject;

/**
 * @author Tomasz Kasprzycki
 */
public class RunTempoCalculator extends ValueObject {

    private final Long fiveKDistanceInMeters = 5000L;
    private final RaceResult raceResult;
    private final double riegelPredictorTimeInSeconds;

    public RunTempoCalculator(RaceResult raceResult) {
        this.raceResult = raceResult;
        this.riegelPredictorTimeInSeconds = calculateRiegelRacePredictor(raceResult);
    }

    public RunTempos calculateRunTempo(RaceResult raceResult) {
        return new RunTempos(
            calculateEasyTempo(),
            calculateMaximumOxygenSpeedFormTempo(),
            calculateLonRunMaxTempo(),
            calculateLongRunMinTempo(),
            calculateYasso800Tempo()
        );
    }

    public double calculateRiegelRacePredictor(RaceResult raceResult) {
        final double t2 = raceResult.getTimeInSeconds() *
            Math.pow(((float) fiveKDistanceInMeters / raceResult.getDistanceInMeters()), 1.06d);
        return t2;
    }


    public long calculateEasyTempo() {
        return (long) (riegelPredictorTimeInSeconds / TempoParams.EASY_TEMPO) / 60 + 5;
    }

    public long calculateMaximumOxygenSpeedFormTempo() {
        return (long) ((riegelPredictorTimeInSeconds / TempoParams.MAXIMUM_OXYGEN) / 60 + 3);
    }

    public long calculateLonRunMaxTempo() {
        return (long) ((riegelPredictorTimeInSeconds / TempoParams.LONG_RUN_MAX) / 60 + 2);
    }

    public long calculateLongRunMinTempo() {
        return (long) ((riegelPredictorTimeInSeconds / TempoParams.LONG_RUN_MIN) / 60 + 7);
    }

    public long calculateYasso800Tempo() {
        return (long) ((riegelPredictorTimeInSeconds / TempoParams.YASSO_800) / 60 + 2);
    }

}
